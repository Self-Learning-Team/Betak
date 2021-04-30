package com.example.betak.ui.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.betak.R
import com.example.betak.databinding.ActivityModifyBinding
import com.example.betak.model.utils.getListArea
import com.example.betak.model.utils.getSuccessArea
import com.example.betak.model.utils.listGovernators
import com.example.betak.model.utils.listJobs
import com.example.betak.model.viewModel.ModifyViewModel
import com.example.betak.model.viewModel.ProfileMeViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ModifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModifyBinding
    private lateinit var viewModel: ModifyViewModel
    private lateinit var viewModel2: ProfileMeViewModel

    private val TAG = "ModifyActivity"
    private lateinit var mAuth: FirebaseAuth
    private lateinit var id: String
    private lateinit var dialoge: AlertDialog
    private lateinit var imageUrl: String

    val REQUEST_CODE = 100
    val storageRef = FirebaseStorage.getInstance().reference
    val fileRef = storageRef.child("PROFILES")

    private var job1: String = ""
    private var area1: String = ""
    private var governator1: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialoge = SpotsDialog
                .Builder()
                .setContext(this)
                .setMessage(R.string.wait)
                .setCancelable(true)
                .build()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_modify)
        viewModel = ViewModelProviders.of(this).get(ModifyViewModel::class.java)
        viewModel2 = ViewModelProviders.of(this).get(ProfileMeViewModel::class.java)

        mAuth = FirebaseAuth.getInstance()
        id = mAuth.currentUser!!.uid

        GlobalScope.launch {
            viewModel.getCurrentUserInfo(id)

        }

        //set same user informaton----===----
        viewModel.employee.observe(this, Observer {
            binding.name.setText(it.getName())
            binding.phone.setText(it.getPhone())
            binding.whats.setText(it.getWhatsApp())
            if (it.getImagePath() != "") {
                Glide
                        .with(this@ModifyActivity)
                        .load(it.getImagePath())
                        .centerCrop()
                        .placeholder(R.drawable.profile)
                        .into(binding.imgProfile);
            }
            setUpSpinners(it.getJob() , it.getArea() , it.getGovernator())

        })


        binding.imgProfile.setOnClickListener {
            openGalleryForImage()
        }

        binding.imgBack.setOnClickListener {
            finish();
        }

        binding.btnComplete.setOnClickListener {
            dialoge.show()
            if (!TextUtils.isEmpty(binding.name.text.toString()) && !TextUtils.isEmpty(binding.phone.text.toString()) && !TextUtils.isEmpty(binding.whats.text.toString())) {
                viewModel.completeModify(id, binding.name.text.toString(),
                        binding.whats.text.toString()
                        , binding.phone.text.toString(),
                        area1, governator1
                        , job1)

                GlobalScope.launch {
                    viewModel2.getCurrentUserInfo(id)
                }
                dialoge.dismiss()
                finish()
            } else {
                dialoge.dismiss()
                Toast.makeText(this, "عدم ترك حقول فارغة", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun uploadImageToStorage(id: String, uri: Uri) {
        val uploadTask = fileRef.child("/" + id + ".png")
        uploadTask.putFile(uri)
                .addOnSuccessListener(
                        OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                            taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                                imageUrl = it.toString()
                                Log.e("sahimaa success load image", imageUrl)
                                modifyUserImagae(imageUrl)
                            }
                        })?.addOnFailureListener(OnFailureListener { e ->
                    Log.e("sahimaa error load image", e.message.toString())
                })
    }

    private fun modifyUserImagae(downloadImage: String) {
        viewModel.modifyImageUser(id, downloadImage)
    }


    private fun setUpSpinners(job : String , area : String , governator:String) {

        val defaultGov = listGovernators.indexOf(governator)

        val listAreas = getListArea(defaultGov)

        val defaultArea = listAreas.indexOf(area)

        val defaultJob = listJobs.indexOf(job)

        listAreas.remove(area)
        listAreas.add(0 , area)

        var AreaAdapter : ArrayAdapter<String> =
                ArrayAdapter<String>(this, R.layout.spinner_list, listAreas);
        AreaAdapter.setDropDownViewResource(R.layout.spinner_list);
        binding.areaSpinner.adapter = AreaAdapter

        binding.areaSpinner.onItemSelectedListener =
                (object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            position: Int,
                            p3: Long
                    ) {

                        area1 = p0?.getItemAtPosition(position).toString()

                    }

                })


        var JobsAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this, R.layout.spinner_list, listJobs);
        JobsAdapter.setDropDownViewResource(R.layout.spinner_list);
        binding.jobsSpinner.adapter = JobsAdapter
        binding.jobsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                job1 = p0?.getItemAtPosition(p2).toString()
            }
        }


        val GovernatorAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this, R.layout.spinner_list, listGovernators);
        GovernatorAdapter.setDropDownViewResource(R.layout.spinner_list);

        binding.governatorSpinner.adapter = GovernatorAdapter
        binding.governatorSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        governator1 = p0?.getItemAtPosition(p2).toString()

                        AreaAdapter = getSuccessArea(this@ModifyActivity , p2)
                        AreaAdapter.setDropDownViewResource(R.layout.spinner_list);
                        binding.areaSpinner.adapter = AreaAdapter
                    }
                }

        binding.jobsSpinner.setSelection(defaultJob)
        binding.governatorSpinner.setSelection(defaultGov)
       // binding.areaSpinner.setSelection(defaultArea)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            binding.imgProfile.setImageURI(data!!.data!!)
            uploadImageToStorage(id, data.data!!)
        }
    }
}