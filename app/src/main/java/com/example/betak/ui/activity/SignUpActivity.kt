package com.example.betak.ui.activity

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.betak.R
import com.example.betak.databinding.ActivitySignUpBinding
import com.example.betak.model.utils.getSuccessArea
import com.example.betak.model.utils.listGovernators
import com.example.betak.model.utils.listJobs
import com.example.betak.model.viewModel.EmployeeInfoViewModel
import com.example.betak.model.viewModel.viewModelFactory
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private var mAuth: FirebaseAuth? = null
    private var mCallbacks: OnVerificationStateChangedCallbacks? = null

    private  var job : String ="ممرضة"
    private var area : String ="الاسكندرية"
    private  var governator : String = "المنتزه"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = DataBindingUtil.setContentView(this , R.layout.activity_sign_up)

        mAuth= FirebaseAuth.getInstance()


        setUpSpinners()

        binding.btnSignUp.setOnClickListener {
       sendVerification()


        }
    }

    private fun sendVerification() {

        if (!TextUtils.isEmpty(binding.name.text.toString()) &&!TextUtils.isEmpty(binding.phone.text.toString()) &&
            !TextUtils.isEmpty(binding.whats.text.toString())  ){

            mCallbacks = object : OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    try {
                    } catch (e: Exception) {
                        Log.e("TAg", e.message!!)
                    }

                }

                override fun onVerificationFailed(e: FirebaseException) {
                    try {
                    } catch (e: Exception) {
                        Log.e("TAg", e.message!!)
                    }
                    Toast.makeText(this@SignUpActivity , e.message , Toast.LENGTH_LONG).show()
                }

                override fun onCodeSent(
                    s: String,
                    forceResendingToken: ForceResendingToken
                ) {
                    super.onCodeSent(s, forceResendingToken)

                    Toast.makeText(this@SignUpActivity , "success" , Toast.LENGTH_LONG).show()

                    val sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
                    val myEdit = sharedPreferences.edit()

                    myEdit.putString("ID", s)
                    myEdit.apply()


                }
            }

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
               "+2"+  binding.phone.text.toString(),  // Phone number to verify
                60,  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                this,  // Activity (for callback binding)
                mCallbacks!!
            )

             var intent = Intent(this@SignUpActivity, EnterCodeActivity::class.java)
            intent.putExtra("name" , binding.name.text.toString())
            intent.putExtra("job" , job)
            intent.putExtra("area" , area)
            intent.putExtra("governator" , governator)
            intent.putExtra("phone" , binding.phone.text.toString())
            intent.putExtra("whatsApp" , binding.whats.text.toString())

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

            startActivity(intent)

        }else{

            Toast.makeText(this , "عدم ترك حقول فارغة" , Toast.LENGTH_LONG).show()
        }

    }

    private fun setUpSpinners() {

        var GovernatorAdapter : ArrayAdapter<String> =  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGovernators);
        GovernatorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        var JobsAdapter : ArrayAdapter<String> =  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listJobs);
         JobsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        binding.governatorSpinner.setAdapter(GovernatorAdapter)
        binding.governatorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
             governator = p0?.getItemAtPosition(p2).toString()
            } }

        binding.jobsSpinner.adapter= JobsAdapter
        binding.jobsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               job = p0?.getItemAtPosition(p2).toString()
            }

        }


        binding.governatorSpinner.onItemSelectedListener = (object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                      getSuccessArea(this@SignUpActivity , position).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                      binding.areaSpinner.adapter = getSuccessArea(this@SignUpActivity , position)

                area =  p0?.getItemAtPosition(position).toString()

            }

        })

    }
}