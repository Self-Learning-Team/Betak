package com.example.betak.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.betak.R
import com.example.betak.databinding.ActivitySignUpBinding
import com.example.betak.model.utils.getSuccessArea
import com.example.betak.model.utils.listGovernators
import com.example.betak.model.utils.listJobs
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import dmax.dialog.SpotsDialog
import java.util.concurrent.TimeUnit

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private var mCallbacks: OnVerificationStateChangedCallbacks? = null

    private var job: String = "ممرضة"
    private var area: String = "الاسكندرية"
    private var governator: String = "المنتزه"

    private lateinit var dialoge: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialoge = SpotsDialog
            .Builder()
            .setContext(this)
            .setMessage(R.string.wait)
            .setCancelable(true)
            .build()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        binding.imgBack.setOnClickListener {
            finish()
        }

        setUpSpinners()

        binding.btnComplete.setOnClickListener {
            sendVerification()
        }
    }

    private fun sendVerification() {

        if (!TextUtils.isEmpty(binding.name.text.toString()) && !TextUtils.isEmpty(binding.phone.text.toString()) &&
            !TextUtils.isEmpty(binding.whats.text.toString())
        ) {
            dialoge.show()

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
                    if (dialoge.isShowing) dialoge.dismiss()
                    Toast.makeText(this@SignUpActivity, e.message, Toast.LENGTH_LONG).show()
                }

                override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                    super.onCodeSent(s, forceResendingToken)

                    if (dialoge.isShowing) dialoge.dismiss()

                    val intent = Intent(this@SignUpActivity, EnterCodeActivity::class.java)
                    intent.putExtra("name", binding.name.text.toString())
                    intent.putExtra("job", job)
                    intent.putExtra("image", "")
                    intent.putExtra("area", area)
                    intent.putExtra("governator", governator)
                    intent.putExtra("phone", binding.phone.text.toString())
                    intent.putExtra("whatsApp", binding.whats.text.toString())
                    intent.putExtra("code", s)

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)

                }
            }
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+2" + binding.phone.text.toString(),  // Phone number to verify
                60,  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                this,  // Activity (for callback binding)
                mCallbacks!!
            )
        } else {
            dialoge.dismiss()
            Toast.makeText(this, "عدم ترك حقول فارغة", Toast.LENGTH_LONG).show()
        } }

    private fun setUpSpinners() {

        var GovernatorAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGovernators);
        GovernatorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        var JobsAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listJobs);
        JobsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        binding.governatorSpinner.setAdapter(GovernatorAdapter)
        binding.governatorSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    governator = p0?.getItemAtPosition(p2).toString()
                }
            }

        binding.jobsSpinner.adapter = JobsAdapter
        binding.jobsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                job = p0?.getItemAtPosition(p2).toString()
            }

        }


        binding.governatorSpinner.onItemSelectedListener =
            (object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {

                    getSuccessArea(
                        this@SignUpActivity,
                        position
                    ).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.areaSpinner.adapter = getSuccessArea(this@SignUpActivity, position)

                    area = p0?.getItemAtPosition(position).toString()

                }

            })

    }
}