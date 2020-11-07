package com.white.employeeapp.ui.employeeinfo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.white.employeeapp.data.entities.EmployeeInfo
import com.white.employeeapp.databinding.ItemEmployeeBinding
import kotlinx.android.synthetic.main.item_employee.view.*


class EmployeeListAdapter(private val listener: EmployeeItemListener) : RecyclerView.Adapter<EmployeeViewHolder>() {

    interface EmployeeItemListener {
        fun onClickedemployee(employeeId: Int)
    }

    private val items = ArrayList<EmployeeInfo>()

    fun setItems(items: ArrayList<EmployeeInfo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding: ItemEmployeeBinding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) = holder.bind(items[position])
}

class EmployeeViewHolder(private val itemBinding: ItemEmployeeBinding, private val listener: EmployeeListAdapter.EmployeeItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var employee: EmployeeInfo

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: EmployeeInfo) {
        this.employee = item
        itemBinding.name.text = item.name
        itemBinding.email.text = """${item.email}"""
        Glide.with(itemBinding.root)
            .load(item.profile_image)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedemployee(employee.id)
    }
}