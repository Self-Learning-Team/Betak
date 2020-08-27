package com.example.betak.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betak.model.entity.Employee
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel() {

    var employeeRef = FirebaseFirestore.getInstance().collection("Employees")


    var _address = MutableLiveData<String>()
    val address: LiveData<String>
        get() = _address

    var _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    var _phone = MutableLiveData<String>()
    val phone: LiveData<String>
        get() = _phone

    var _whatsApp = MutableLiveData<String>()
    val whatsApp: LiveData<String>
        get() = _whatsApp


      fun getProfileInfo(id : String) {

            GlobalScope.launch(Dispatchers.IO) {

             var   employee = employeeRef.document(id).get().await().toObject(com.example.betak.model.entity.Employee::class.java)

                retrieveData(employee!!)
            }
    }

     suspend fun retrieveData(employee: Employee){

        withContext(Dispatchers.Main) {

            _address.value = StringBuilder(employee.getGovernator()).append(" , ").append(employee.getArea()).toString()
            _name.value = employee!!.getName()
            _phone.value = employee!!.getPhone()
            _whatsApp.value = employee!!.getWhatsApp()
        }

    }
}