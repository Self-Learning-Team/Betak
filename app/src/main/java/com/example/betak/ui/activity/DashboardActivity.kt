package com.example.betak.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.betak.MainActivity
import com.example.betak.R
import com.example.betak.databinding.ActivityDashboardBinding
import com.example.betak.model.fcm.FcmCommon
import com.example.betak.model.viewModel.DashboardViewModel
import com.example.betak.model.viewModel.EmployeeInfoViewModel
import com.example.betak.model.viewModel.viewModelFactory
import com.google.android.gms.common.internal.service.Common
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var id: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var viewModel: DashboardViewModel


    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            viewModel.getCurrentUserInfo(id)
            viewModel.getNotification(id)
            viewModel.getNonOpenNotification(id)

        }

        viewModel.employee.observe(this, Observer {
            binding.textWelcome.text = getGreetingMessage() + " " + it.getName()

            Glide.with(this)
                    .load(it.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .into(binding.imgProfile)
        })
        viewModel.open.observe(this, Observer {
            val size = it.size
            binding.notificationBadge.setText(size.toString(), true)

        })
    }

    override fun onRestart() {
        super.onRestart()
        GlobalScope.launch {
            viewModel.getCurrentUserInfo(id)
            viewModel.getNotification(id)
            viewModel.getNonOpenNotification(id)

        }

        viewModel.employee.observe(this, Observer {
            binding.textWelcome.text = getGreetingMessage() + " " + it.getName()

            Glide.with(this)
                    .load(it.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .into(binding.imgProfile)
        })
        viewModel.open.observe(this, Observer {
            val size = it.size
            binding.notificationBadge.setText(size.toString(), true)

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        mAuth = FirebaseAuth.getInstance()
        id = mAuth.currentUser!!.uid

        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        FirebaseInstanceId.getInstance()
                .instanceId.addOnSuccessListener { instanceIdResult ->
                    FcmCommon.updateToken(instanceIdResult.token)
                    Log.e("my token", instanceIdResult.token)

                }.addOnFailureListener {
                    Log.e("error token", it.message.toString())
                }

        GlobalScope.launch {
            viewModel.getNotification(id)
            viewModel.getCurrentUserInfo(id)
            viewModel.getNonOpenNotification(id)
        }

        viewModel.employee.observe(this, Observer {
            binding.textWelcome.text = getGreetingMessage() + " " + it.getName()
            Glide.with(this)
                    .load(it.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .into(binding.imgProfile)
        })

        binding.notifications.setOnClickListener {

            val intent = Intent(this, NotificationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        viewModel.open.observe(this, Observer {
            val size = it.size
            Log.e("shaima", "temp size is" + size.toString())
          //  binding.notificationBadge.setText(size.toString(), true)
            binding.notificationBadge.setNumber(size , true)


        })
        binding.imgProfile.setOnClickListener {

            val intent = Intent(this, MeProfileActivity::class.java)
            intent.putExtra("id", id)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.search.setOnClickListener {
            var intent = Intent(this, FilterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        binding.teatcherCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "مدرس")
            startActivity(intent)
        }
        binding.nurseCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "ممرضة")
            startActivity(intent)
        }
        binding.plumberCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "سباك")
            startActivity(intent)
        }
        binding.smithCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "حداد")
            startActivity(intent)
        }
        binding.electricCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "كهربائي")
            startActivity(intent)
        }
        binding.operatorCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "عامل سويتش")
            startActivity(intent)
        }
        binding.cookerCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "طباخ")
            startActivity(intent)
        }
        binding.homeCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "أدوات منزلية")
            startActivity(intent)
        }
        binding.barberCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "حلاق")
            startActivity(intent)
        }
        binding.cleanerCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "عمال نظافة")
            startActivity(intent)
        }
        binding.hairCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "كوافير")
            startActivity(intent)
        }
        binding.babysetterCard.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "مربية أطفال")
            startActivity(intent)
        }
        binding.painter.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "عامل دهان")
            startActivity(intent)
        }
        binding.ceramic.setOnClickListener {
            var intent = Intent(this, EmloyeesActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("job", "عامل سيراميك")
            startActivity(intent)
        }

    }

    fun getGreetingMessage(): String {
        val c = Calendar.getInstance()
        val timeOfDay = c.get(Calendar.HOUR_OF_DAY)

        return when (timeOfDay) {
            in 0..11 -> "صباح الخير يا"
            in 12..23 -> "مساء الخير يا"
            else -> "Hello"
        }
    }
}