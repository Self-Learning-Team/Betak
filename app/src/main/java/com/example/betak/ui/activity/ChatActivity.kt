package com.example.betak.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.betak.R
import com.example.betak.databinding.ActivityChatBinding
import com.example.betak.model.entity.Chat
import com.example.betak.model.fcm.MyToken
import com.example.betak.model.viewModel.ChatViewModel
import com.example.betak.model.viewModel.DashboardViewModel
import com.example.betak.ui.adapter.ChatAdapter
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ChatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding
    private lateinit var viewModel : ChatViewModel
    private lateinit var viewModel2 : DashboardViewModel

    private lateinit var chats : List<Chat>
    private lateinit var id : String
    private lateinit var mAuth : FirebaseAuth
    private lateinit var uid : String
    private lateinit var timestamp: Timestamp

    @SuppressLint("SimpleDateFormat")
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chats = ArrayList()
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_chat)

        viewModel= ViewModelProviders.of(this).get(ChatViewModel::class.java)
        viewModel2= ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        timestamp = Timestamp.now()

        binding.recyclerView.smoothScrollToPosition(0);

        id = intent.getStringExtra("id")!!
        mAuth = FirebaseAuth.getInstance()
        uid = mAuth.currentUser!!.uid

        binding.back.setOnClickListener {
          finish()
        }


        GlobalScope.launch {
            viewModel.getAllMessages(id)
            viewModel.getCurrentUserInfo(id)
            viewModel2.getNotification(uid)
        }

        viewModel.employee.observe(this , Observer {
         binding.txtReceiver.text = it.getName()
            Glide.with(this)
                .load(it.getImagePath())
                .centerCrop()
                .placeholder(R.drawable.profile)
                .into(binding.imgReceiver);
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
                 uid,
                 id,
                 "jjs",
                 "sjs",
                 binding.enterMessage.text.toString(),
                 timestamp
             )
             binding.enterMessage.text.clear()
             //update immediately
             viewModel.getAllMessages(id)
             binding.recyclerView.smoothScrollToPosition(0)

             //get all receiver notifications
             viewModel2.getNotification(id)

         }

        }else{

         Toast.makeText(this , "لا يمكن ارسال رساله فارغة" , Toast.LENGTH_LONG).show()
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


    }



    override fun onStart() {
        viewModel.onlineUser(uid , true)
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
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