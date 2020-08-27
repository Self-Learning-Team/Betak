package com.example.betak.ui.activity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.betak.R
import com.example.betak.databinding.ActivityEnterCodeBinding
import com.example.betak.model.viewModel.EmployeeInfoViewModel
import com.example.betak.model.viewModel.viewModelFactory
import com.example.betak.repository.EmployeeInfoRepo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*

class EnterCodeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEnterCodeBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var code : String

    private lateinit var viewModel : EmployeeInfoViewModel
    private lateinit var viewModelFactory : viewModelFactory

    private lateinit var name : String
    private lateinit var job : String
    private lateinit var area : String
    private lateinit var governator : String
    private lateinit var phone : String
    private lateinit var whats : String

    @SuppressLint("Assert")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = DataBindingUtil.setContentView(this , R.layout.activity_enter_code)
     mAuth = FirebaseAuth.getInstance()

        var intent = intent

      name= intent.getStringExtra("name")!!
       job = intent.getStringExtra("job" )!!
      area = intent.getStringExtra("area" )!!
       governator = intent.getStringExtra("governator")!!
      phone= intent.getStringExtra("phone" )!!
      whats= intent.getStringExtra("whatsApp" )!!

         viewModelFactory = viewModelFactory(applicationContext as Application)
         viewModel= ViewModelProviders.of(this , viewModelFactory).get(EmployeeInfoViewModel::class.java)


        val sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val verificationId = sharedPreferences.getString("ID", " 000000")

        binding.firstPinView.setPinViewEventListener { pinview, fromUser ->

             code = pinview.value.toString()

            Toast.makeText(this@EnterCodeActivity, code, Toast.LENGTH_LONG).show()
        }

        binding.btnEnter.setOnClickListener {

                assert(verificationId != null)

                    val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
                    signInWithPhoneAuthCredential(credential)
                }

            }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        mAuth.signInWithCredential(credential).addOnCompleteListener(this,
            OnCompleteListener { task: Task<AuthResult?> ->
                if (task.isSuccessful) {

                     var intent =Intent(this@EnterCodeActivity, DashboardActivity::class.java)

                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                     startActivity(intent)

                     saveEmployeeData()

                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                  Toast.makeText(this , task.exception.toString() , Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

    private fun saveEmployeeData() {

        viewModel.setEmployeeInfo(mAuth.currentUser!!.uid , name , job , phone , whats, area, governator)

    }
}