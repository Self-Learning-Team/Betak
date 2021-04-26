package com.example.betak.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betak.model.entity.Employee
import com.example.betak.model.entity.Rating
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel() {

    var employeeRef = FirebaseFirestore.getInstance().collection("Employees")


    var _emp = MutableLiveData<Employee>()
    val emp: LiveData<Employee>
        get() = _emp

    var _ratings = MutableLiveData<List<Rating>>()
    val ratings: LiveData<List<Rating>>
        get() = _ratings


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

    var ratingRef = FirebaseFirestore.getInstance().collection("Ratings")

    fun getProfileInfo(id: String) {

        GlobalScope.launch(Dispatchers.IO) {

            var employee = employeeRef.document(id).get().await()
                .toObject(com.example.betak.model.entity.Employee::class.java)

            retrieveData(employee!!)
        }
    }

    suspend fun retrieveData(employee: Employee) {

        withContext(Dispatchers.Main) {
            _address.value = StringBuilder(employee.getGovernator()).append(",").append(employee.getArea()).toString()
            _name.value = employee!!.getName()
            _phone.value = employee!!.getPhone()
            _whatsApp.value = employee!!.getWhatsApp()
        }

    }

       fun addRating(id: String  , myId : String , op: String, rate: Float) {
        GlobalScope.launch {
            val rating = Rating(opinion = op, rate = rate , clientId = myId);
            ratingRef.document(id).collection("rate").add(rating)
        }
    }

       suspend fun getRatings(id: String) {

        val ratings: ArrayList<Rating> = ArrayList();

        withContext(Dispatchers.IO) {
            val snapShot = ratingRef.document(id).collection("rate").get().await();
            snapShot.forEach {
            ratings.add(it.toObject(Rating::class.java));
            }
        }

        withContext(Dispatchers.Main){
         _ratings.value = ratings
        }
    }



    suspend fun getCurrentUserInfo(id : String ){
        var current : Employee = Employee()

        withContext(Dispatchers.IO){
            val ref = employeeRef.document(id).get().await()
            current = ref.toObject(Employee::class.java)!!

        }

        withContext(Dispatchers.Main){
            _emp.value = current
        }

    }
}