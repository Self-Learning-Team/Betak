package com.example.betak.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.betak.R
import com.example.betak.databinding.ActivityFilterBinding
import com.example.betak.model.utils.getSuccessArea
import com.example.betak.model.utils.listGovernators
import com.example.betak.model.utils.listJobs

class FilterActivity : AppCompatActivity() {

    private lateinit var binding :  ActivityFilterBinding

    private var job : String = "ممرضة"
    private var area : String ="الاسكندرية"
    private var governator : String="المنتزه"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      binding = DataBindingUtil.setContentView(this , R.layout.activity_filter)

        setUpSpinners()

      binding.imgBack.setOnClickListener {
          finish()
      }

        binding.btnSearch.setOnClickListener {
            val intent = Intent(this , EmFilterActivity::class.java)

            intent.putExtra("job" , job)
            intent.putExtra("area" , area)
            intent.putExtra("governator" , governator)

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }

    private fun setUpSpinners() {

        var GovernatorAdapter : ArrayAdapter<String> =  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGovernators);
        GovernatorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        var JobsAdapter : ArrayAdapter<String> =  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listJobs);
        JobsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        binding.governatorSpinner.setAdapter(GovernatorAdapter)
        binding.jobsSpinner.adapter= JobsAdapter

        binding.jobsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               job = p0!!.getItemAtPosition(p2).toString()
            } }

        binding.areaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                area = p0!!.getItemAtPosition(p2).toString()
            } }


        binding.governatorSpinner.onItemSelectedListener = (object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                getSuccessArea(this@FilterActivity , position).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.areaSpinner.adapter = getSuccessArea(this@FilterActivity , position)

                governator = p0!!.getItemAtPosition(position).toString() }

        })

    }
}