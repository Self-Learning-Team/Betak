package com.example.betak.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.betak.R
import com.example.betak.databinding.ActivityChatBinding
import com.example.betak.model.entity.Chat
import com.example.betak.model.utils.Offline
import com.example.betak.model.viewModel.ChatViewModel
import com.example.betak.model.viewModel.DashboardViewModel
import com.example.betak.ui.adapter.ChatAdapter
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {


    private lateinit var binding : ActivityChatBinding
    private lateinit var viewModel : ChatViewModel
    private lateinit var viewModel2 : DashboardViewModel
    private lateinit var chats : List<Chat>
    private lateinit var id : String
    private lateinit var mAuth : FirebaseAuth
    private lateinit var uid : String
    private lateinit var timestamp: Timestamp
    private  lateinit var time : Date

   private val currentTime = System.currentTimeMillis()

    @SuppressLint("SimpleDateFormat")
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chats = ArrayList()
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_chat)

        viewModel= ViewModelProviders.of(this).get(ChatViewModel::class.java)
        viewModel2= ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        timestamp = Timestamp.now()

        binding.recyclerView.smoothScrollToPosition(0);

        time =  Calendar.getInstance().getTime();


        id = intent.getStringExtra("id")!!
        mAuth = FirebaseAuth.getInstance()
        uid = mAuth.currentUser!!.uid

        binding.back.setOnClickListener {
          finish()
        }

        GlobalScope.launch {
            viewModel.getCurrentUserInfo(id)
            viewModel.updateStartDateMessages()

        }

        updateMessages()

        viewModel.employee.observe(this , Observer {
         binding.txtReceiver.text = it.getName()
            Glide.with(this)
                .load(it.getImagePath())
                .centerCrop()
                .placeholder(R.drawable.profile)
                .into(binding.imgReceiver);

            // check online or not ---===---
            if(it.getOnApp()==true){
               binding.txtOnApp.visibility= View.VISIBLE
            }else{
                binding.txtOnApp.visibility= View.GONE

            }
        })

        viewModel.chats.observe(this , androidx.lifecycle.Observer {
            if(it.size > 0){
                chats = it
            }else{
             chats = ArrayList()
            }
            binding.recyclerView.adapter = ChatAdapter(chats)

        })

        binding.btnSend.setOnClickListener {
        if(!binding.enterMessage.text.isEmpty()){

         GlobalScope.launch {
         viewModel.addNewMessage(
                 currentTime,
                 time,
                 uid,
                 id,
                 "jjs",
                 "sjs",
                 binding.enterMessage.text.toString(),
                 timestamp
             )
             //update immediately
             updateMessages()
             binding.enterMessage.text.clear()

             binding.recyclerView.smoothScrollToPosition(0)
         }
        }else{

         Toast.makeText(this , "لا يمكن ارسال رساله فارغة" , Toast.LENGTH_LONG).show()
        }
        }
    }

       fun updateMessages() {

        GlobalScope.launch {
            viewModel.updateSeenMessages(id)
            viewModel.getAllMessages(id)
            viewModel2.getNotification(id)
        }
    }


    override fun onStart() {
        viewModel.onlineUser(uid , true)
        updateMessages()
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        updateMessages()

        viewModel.onlineUser(uid , true)
    }

    override fun onPause() {
        viewModel.onlineUser(uid , false)
        super.onPause()
        GlobalScope.launch {
            viewModel2.getNotification(uid)

        }
    }

    override fun onStop() {
        viewModel.onlineUser(uid , false)
        GlobalScope.launch {
        viewModel2.getNotification(uid)
        }
        super.onStop()
    }

    override fun onDestroy() {
        viewModel.onlineUser(uid , false)
        GlobalScope.launch {
            viewModel2.getNotification(uid)

        }
        super.onDestroy()
    }
}
/*
        binding.enterLocation.setOnClickListener {
     //init places
             Places.initialize(applicationContext , getString(R.string.api_key))

             ///init place field list
             var fieldList: List<Place.Field> = Arrays.asList(Place.Field.ADDRESS , Place.Field.LAT_LNG , Place.Field.NAME)
             var intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY , fieldList).build(this)
             startActivityForResult(intent , 100)
        }*/