package com.example.betak.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.betak.R
import com.example.betak.databinding.ActivityProfileBinding
import com.example.betak.model.viewModel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth


class ProfileActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    private lateinit var binding : ActivityProfileBinding
    private lateinit var viewModel : ProfileViewModel

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this , R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()

        viewModel= ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        var id = intent.getStringExtra("id")

        viewModel.getProfileInfo(id!!)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel


       binding.whatsApp.setOnClickListener {
           val url = "https://api.whatsapp.com/send?phone=${binding.whatsApp.text.toString()}"
           val i = Intent(Intent.ACTION_VIEW)
           i.data = Uri.parse(url)
           startActivity(i)
       }

        
        binding.phone.setOnClickListener {
            //phoneNumber
            var intent = Intent(Intent.ACTION_DIAL , Uri.parse("tel:"+Uri.encode(binding.phone.text.toString())))
            startActivity(intent)

        }


    }

}