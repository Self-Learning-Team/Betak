package com.example.betak.ui.activity

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.betak.MainActivity
import com.example.betak.R
import com.example.betak.databinding.ActivityDashboardBinding
import com.example.betak.model.viewModel.EmployeeInfoViewModel
import com.example.betak.model.viewModel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding :  ActivityDashboardBinding
    private lateinit var viewModel : EmployeeInfoViewModel
    private lateinit var viewModelFactory : viewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      binding= DataBindingUtil.setContentView(this , R.layout.activity_dashboard)

        var  intent = intent



        binding.imgProfile.setOnClickListener {

            var intent = Intent(this , ProfileActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        if (FirebaseAuth.getInstance().currentUser==null && !intent.getStringExtra("in").equals("no")){

         var intent = Intent(this , MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        if(intent.getStringExtra("in").equals("no")){
        binding.imgProfile.visibility = View.GONE
        }

        binding.search.setOnClickListener {
            var intent = Intent(this , FilterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.teatcherCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "مدرس")
            startActivity(intent)
        }
        binding.nurseCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "ممرضة")
            startActivity(intent)
        }

        binding.plumberCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "سباك")
            startActivity(intent)
        }

        binding.smithCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "حداد")
            startActivity(intent)
        }
        binding.electricCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "كهربائي")
            startActivity(intent)
        }
        binding.operatorCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "عامل سويتش")
            startActivity(intent)
        }

        binding.cookerCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "طباخ")
            startActivity(intent)
        }
        binding.homeCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "أدوات منزلية")
            startActivity(intent)
        }
        binding.barberCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "حلاق")
            startActivity(intent)
        }
        binding.cleanerCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "عمال نظافة")
            startActivity(intent)
        }
        binding.hairCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "كوافير")
            startActivity(intent)
        }
        binding.babysetterCard.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "مربية أطفال")
            startActivity(intent)
        }
        binding.painter.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "عامل دهان")
            startActivity(intent)
        }
        binding.ceramic.setOnClickListener {
            var intent = Intent(this , EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job" , "عامل سيراميك")
            startActivity(intent)
        }

    }
}