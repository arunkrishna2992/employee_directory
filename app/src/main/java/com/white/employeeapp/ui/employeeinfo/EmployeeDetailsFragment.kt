package com.white.employeeapp.ui.employeeinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.white.employeeapp.data.entities.EmployeeInfo
import com.white.employeeapp.databinding.EmployeeDetailFragmentBinding
import com.white.employeeapp.utils.Resource
import com.white.employeeapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.employee_detail_fragment.view.*
import kotlinx.android.synthetic.main.item_employee.view.*


@AndroidEntryPoint
class EmployeeDetailsFragment : Fragment() {

    private var binding: EmployeeDetailFragmentBinding by autoCleared()
    private val viewModel: EmployeeInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmployeeDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("id")?.let {
            viewModel.start(it)
        }
        setupObservers()
    }

    private fun setupObservers() {

/*
        viewModel.empoyees.observe(viewLifecycleOwner, Observer {
            */
/*if (it!=null) {
                    bindEmployee(it.data?.get(viewModel.employeeId)!!)
                    binding.progressBar.visibility = View.GONE
                    binding.employeeCl.visibility = View.VISIBLE
                }*//*

        })
*/

        // Bad code, need to work on getting data from db
        viewModel.empoyees.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    var list = ArrayList(it.data)
                    for (employee in list){
                        if(employee.id == viewModel.employeeId){
                            bindEmployee(employee)
                        }
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }

        })


    }

    private fun bindEmployee(employee: EmployeeInfo) {
        binding.name.text = employee.name
        binding.email.text = employee.email
        binding.username.text = employee.username
        binding.phone.text = employee.phone
        Glide.with(binding.root)
            .load(employee.profile_image)
            .transform(CircleCrop())
            .into(binding.image)
    }
}