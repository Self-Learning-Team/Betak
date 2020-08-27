package com.example.betak.model.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.betak.model.entity.Employee
import com.example.betak.ui.adapter.EmployeeAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Employee>?){
    val adapter = recyclerView.adapter as EmployeeAdapter

    adapter.submitList(data)
}
@BindingAdapter("setName")
fun bindName(textView: TextView , value : String){
    textView.text = value
}
@BindingAdapter("setPhone")
fun bindPhone(textView: TextView , value : String){
    textView.text = value
}
@BindingAdapter("setAddress")
fun bindAddress(textView: TextView , value : String){
    textView.text = value
}

