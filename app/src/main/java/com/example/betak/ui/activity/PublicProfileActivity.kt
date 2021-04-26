package com.example.betak.ui.activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.betak.R
import com.example.betak.databinding.ActivityProfileBinding
import com.example.betak.model.entity.IDialogeClickListner
import com.example.betak.model.utils.CustomDialoge
import com.example.betak.model.viewModel.ProfileViewModel
import com.example.betak.ui.fragment.BottomSheetFragment.Companion.newInstance
import com.example.betak.ui.fragment.BottomSheetFragment.Companion.TAG
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PublicProfileActivity : AppCompatActivity() , IDialogeClickListner{

    private lateinit var mAuth : FirebaseAuth
    private lateinit var binding : ActivityProfileBinding
    private lateinit var viewModel : ProfileViewModel
    private lateinit var id: String
    private lateinit var myId : String

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this , R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()
        myId = mAuth.currentUser!!.uid

        viewModel= ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        id = intent.getStringExtra("id")!!

        viewModel.getProfileInfo(id)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        //setup profile image
        GlobalScope.launch {
            viewModel.getCurrentUserInfo(id)
        }
        viewModel.emp.observe(this , Observer {
            if(it.getImagePath()!=""){
                Log.e("shaimaa imagePath" , it.getImagePath())
                Glide
                    .with(this)
                    .load(it.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .into(binding.profileImg);
            }
        })

        binding.messageMe.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
                intent.putExtra("id" , id)
                startActivity(intent)
        }
        //make actions here
        binding.imgBack.setOnClickListener {
            finish();
        }

        binding.whatsApp.setOnClickListener {
           val url = "https://api.whatsapp.com/send?phone=${binding.whatsApp.text.toString()}"
           val i = Intent(Intent.ACTION_VIEW)
           i.data = Uri.parse(url)
           startActivity(i)
       }

        
        binding.phone.setOnClickListener {
            //phoneNumber
            val intent = Intent(Intent.ACTION_DIAL , Uri.parse("tel:"+Uri.encode(binding.phone.text.toString())))
            startActivity(intent)

        }

        binding.opinion.setOnClickListener {
            val showOpinions = newInstance

            val bundle = Bundle()
            bundle.putString("id", id)
            showOpinions.arguments = bundle

            showOpinions.show(supportFragmentManager, TAG)
        }

        binding.rating.setOnClickListener {
         CustomDialoge.showLoginDialoge(context = this , iDialogeClickListner = this );
        }
    }

    override fun onClickPositiveButton(
        dialogInterface: DialogInterface,
        opinion: String,
        rating: Float
    ) {

        viewModel.addRating(id , myId , opinion , rating)
        dialogInterface.dismiss()
    }


    override fun onClickNegativeButton(dialogInterface: DialogInterface) {
      dialogInterface.dismiss();
    }


}