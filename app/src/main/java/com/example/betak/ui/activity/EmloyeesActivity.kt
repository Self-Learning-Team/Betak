package com.example.betak.ui.activity

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.betak.R
import com.example.betak.databinding.ActivityEmloyeesBinding
import com.example.betak.model.viewModel.EmployeeInfoViewModel
import com.example.betak.model.viewModel.viewModelFactory
import com.example.betak.ui.adapter.EmployeeAdapter

class EmloyeesActivity : AppCompatActivity() {

    private lateinit var viewModel : EmployeeInfoViewModel
    private lateinit var viewModelFactory : viewModelFactory
    private lateinit var binding: ActivityEmloyeesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =DataBindingUtil.setContentView(this , R.layout.activity_emloyees)

        viewModelFactory = viewModelFactory(applicationContext as Application)
        viewModel= ViewModelProviders.of(this , viewModelFactory).get(EmployeeInfoViewModel::class.java)

        var intent = intent
        var job = intent.getStringExtra("job")


        binding.recyclerView.startLayoutAnimation()

        viewModel.getEmployeeInfo(job!!)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel


        binding.recyclerView.adapter = EmployeeAdapter(EmployeeAdapter.OnClickListner{

            var intent = Intent(this , ProfileActivity::class.java)

            intent.putExtra("employee" , it)

            intent.putExtra("id" , it.getId())

            startActivity(intent)
        })



    }
}












