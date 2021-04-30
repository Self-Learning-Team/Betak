package com.example.betak.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.betak.R
import com.example.betak.databinding.ActivityEnterCodeBinding
import com.example.betak.model.utils.Event
import com.example.betak.model.viewModel.EmployeeInfoViewModel
import com.goodiebag.pinview.Pinview
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class EnterCodeActivity : AppCompatActivity() {

    private val TAG = "EnterCodeActivity"
    private lateinit var binding: ActivityEnterCodeBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var codeSent: String

    private lateinit var viewModel: EmployeeInfoViewModel

    private lateinit var name: String
    private lateinit var job: String
    private lateinit var area: String
    private lateinit var governator: String
    private lateinit var phone: String
    private lateinit var whats: String
    private lateinit var otp: String
    private lateinit var image: String
    private var online = false

    var PERMISSION_REQUEST_RECIEVE_SMS =0;

companion object{
  var SMS_RECIEVED = "android.provider.Telephony"
}

    override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun recieveEvent(event : Event){
     Log.e("eventbus recieve is " , event.otp)
     runOnUiThread { binding.pinView.value= event.otp }
     otp = event.otp
    }

    @SuppressLint("Assert")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_code)
        mAuth = FirebaseAuth.getInstance()
        ///start check permissions
        checkPermissions()

        val intent = intent
        name = intent.getStringExtra("name")!!
        job = intent.getStringExtra("job")!!
        image = intent.getStringExtra("image")!!
        area = intent.getStringExtra("area")!!
        governator = intent.getStringExtra("governator")!!
        phone = intent.getStringExtra("phone")!!
        whats = intent.getStringExtra("whatsApp")!!
        codeSent = intent.getStringExtra("code")!!

        Log.e("code is " , codeSent.toString());

        viewModel =
            ViewModelProviders.of(this).get(EmployeeInfoViewModel::class.java)

        binding.pinView.setPinViewEventListener(object : Pinview.PinViewEventListener{
            override fun onDataEntered(pinview: Pinview, fromUser: Boolean) {
                otp = pinview.value
            }
        })

        binding.btnEnter.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(codeSent, otp)
            signInWithPhoneAuthCredential(credential)
        }

    }

    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this , Manifest.permission.RECEIVE_SMS) !=PackageManager.PERMISSION_GRANTED){
       if(ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.RECEIVE_SMS)){

       }else{
        ActivityCompat.requestPermissions(this , listOf(Manifest.permission.RECEIVE_SMS).toTypedArray(), PERMISSION_REQUEST_RECIEVE_SMS)
       }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            when(requestCode){
            PERMISSION_REQUEST_RECIEVE_SMS -> {
               if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                  Toast.makeText(this , "Thank you for granting" , Toast.LENGTH_LONG).show()
               }else{
               Toast.makeText(this , "you should permit first---" , Toast.LENGTH_LONG).show()

               }
            }

            }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        mAuth.signInWithCredential(credential).addOnCompleteListener(this,
            OnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {

                    val intent = Intent(this@EnterCodeActivity, DashboardActivity::class.java)

                    intent.putExtra("id" , mAuth.currentUser!!.uid)

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                    startActivity(intent)

                    saveEmployeeData()

                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

    private fun saveEmployeeData() {

        viewModel.setEmployeeInfo(
            mAuth.currentUser!!.uid,
            name,
            job,
            phone,
            whats,
            area,
            governator,
            image,
            online,
            false
        )

    }


}
