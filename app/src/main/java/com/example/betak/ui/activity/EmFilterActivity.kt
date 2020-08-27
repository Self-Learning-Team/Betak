package com.example.betak.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
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

        var decoration : RecyclerView.ItemDecoration = DividerItemDecoration(this , DividerItemDecoration.HORIZONTAL)
        binding.recyclerView.addItemDecoration(decoration)

        binding.recyclerView.adapter = EmployeeAdapter(EmployeeAdapter.OnClickListner{

        })

    }
}











