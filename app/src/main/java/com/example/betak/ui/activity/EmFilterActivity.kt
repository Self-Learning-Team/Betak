package com.example.betak.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.betak.R
import com.example.betak.databinding.ActivityEmFilterBinding
import com.example.betak.model.viewModel.FilterViewModel
import com.example.betak.ui.adapter.EmployeeAdapter
import java.util.logging.Filter

class EmFilterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEmFilterBinding
    private lateinit var viewModel : FilterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this , R.layout.activity_em_filter)
        viewModel = ViewModelProviders.of(this).get(FilterViewModel::class.java)

       var job = intent.getStringExtra("job")
       var governator=  intent.getStringExtra("area")
       var area =  intent.getStringExtra("governator")

        binding.recyclerView.startLayoutAnimation()

        viewModel.filterEmployeeData(job!! , governator!!, area!!)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

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

        val decoration : RecyclerView.ItemDecoration = DividerItemDecoration(this , DividerItemDecoration.HORIZONTAL)
        binding.recyclerView.addItemDecoration(decoration)

        binding.recyclerView.adapter = EmployeeAdapter(EmployeeAdapter.OnClickListner{

                val intent = Intent(this , PublicProfileActivity::class.java)

                intent.putExtra("employee" , it)

                intent.putExtra("id" , it.getId())

                startActivity(intent)
            })

    }
}











