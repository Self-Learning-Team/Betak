package com.example.betak.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.betak.R
import com.example.betak.databinding.ActivityNotificationBinding
import com.example.betak.model.entity.Noti
import com.example.betak.model.viewModel.DashboardViewModel
import com.example.betak.ui.adapter.NotiAdapter
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    private lateinit var viewModel: DashboardViewModel
    private lateinit var mAuth : FirebaseAuth
    private lateinit var uid: String
    var listNotes : List<Noti> = ArrayList<Noti>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


       binding= DataBindingUtil.setContentView(this , R.layout.activity_notification)
       viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        mAuth= FirebaseAuth.getInstance()
        uid = mAuth.currentUser!!.uid

        Log.e("shaimaa time " , System.currentTimeMillis().toString())

        //check empty of not
        viewModel.notes.observe(this , Observer {
            if(it.size>0){
                listNotes= it
                binding.pgs.visibility = View.GONE

            }else if(it.size ==0){
                binding.notFound.visibility = View.VISIBLE
                binding.pgs.visibility = View.GONE
            }
        })

        GlobalScope.launch {
            viewModel.getNotification(uid)
        }

        binding.recyclerView.adapter = NotiAdapter(listNotes , this)

        binding.imgBack.setOnClickListener {
            finish()
        }

    }
}