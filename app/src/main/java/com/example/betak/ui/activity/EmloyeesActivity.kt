package com.example.betak.ui.activity

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.betak.R
import com.example.betak.databinding.ActivityEmloyeesBinding
import com.example.betak.model.viewModel.EmployeeInfoViewModel
import com.example.betak.ui.adapter.EmployeeAdapter


class EmloyeesActivity : AppCompatActivity() {

    private lateinit var viewModel : EmployeeInfoViewModel
    private lateinit var binding: ActivityEmloyeesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =DataBindingUtil.setContentView(this , R.layout.activity_emloyees)

        viewModel= ViewModelProviders.of(this).get(EmployeeInfoViewModel::class.java)

        val intent = intent
        val job = intent.getStringExtra("job")


        binding.recyclerView.startLayoutAnimation()


        viewModel.getEmployeeInfo(job!!)

        //make actions
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.imgFilter.setOnClickListener {
            val intent = Intent(this , FilterActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

            startActivity(intent)
        }
        //check empty of not
        viewModel.employees.observe(this , Observer {
          if(it.size>0){
           binding.pgs.visibility = View.GONE
          }else if(it.size ==0){
           binding.notFound.visibility = View.VISIBLE
           binding.pgs.visibility = View.GONE
          }
        })

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel


        binding.recyclerView.adapter = EmployeeAdapter(EmployeeAdapter.OnClickListner{

            val intent = Intent(this , PublicProfileActivity::class.java)

            intent.putExtra("employee" , it)

            intent.putExtra("id" , it.getId())

            startActivity(intent)
        })

    }
}












