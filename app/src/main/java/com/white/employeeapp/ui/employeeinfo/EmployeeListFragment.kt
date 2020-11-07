package com.white.employeeapp.ui.employeeinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.white.employeeapp.R
import com.white.employeeapp.databinding.EmployeeFragmentBinding
import com.white.employeeapp.utils.Resource
import com.white.employeeapp.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import java.util.Observer

@AndroidEntryPoint
class EmployeeListFragment : Fragment(), EmployeeListAdapter.EmployeeItemListener {

    private var binding: EmployeeFragmentBinding by autoCleared()
    private val viewModel: EmployeeInfoViewModel by viewModels()
    private lateinit var adapter: EmployeeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmployeeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = EmployeeListAdapter(this)
        binding.employeeRv.layoutManager = LinearLayoutManager(requireContext())
        binding.employeeRv.adapter = adapter
    }

    private fun setupObservers() {

        viewModel.empoyees.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }

        })
    }

    override fun onClickedemployee(employeeId: Int) {
        findNavController().navigate(
            R.id.action_employeeFragment_to_employeeDetailFragment,
            bundleOf("id" to employeeId)
        )
    }
}