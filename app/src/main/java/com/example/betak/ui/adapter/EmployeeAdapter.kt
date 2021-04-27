package com.example.betak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.betak.databinding.EmployeeItemBinding
import com.example.betak.model.entity.Employee
import com.example.betak.model.entity.Noti

class EmployeeAdapter(private val onClickListener: OnClickListner) : ListAdapter<Employee , EmployeeAdapter.viewHolder>(DiffCallback){

    class viewHolder (private  var binding : EmployeeItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Employee){
            binding.employee= item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeAdapter.viewHolder {
    return viewHolder(EmployeeItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: EmployeeAdapter.viewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.getId() == newItem.getId()
        }
    }

    class OnClickListner(val clickListner: (employee : Employee ) -> Unit) {
        fun onClick(employee: Employee) = clickListner(employee)
    }
}