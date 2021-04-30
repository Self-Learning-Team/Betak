package com.example.betak.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betak.model.entity.Employee
import com.example.betak.model.utils.Offline
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProfileMeViewModel : ViewModel() {

    init {
        Offline.setUp()
    }

    var employeeRef = Offline.db.collection("Employees")

    var _empoloyee = MutableLiveData<Employee>()
    val employee: LiveData<Employee>
    get() = _empoloyee


    suspend fun getCurrentUserInfo(id : String ){
        var current : Employee = Employee()

        withContext(Dispatchers.IO){
            val ref = employeeRef.document(id).get().await()
            current = ref.toObject(Employee::class.java)!!

        }

        withContext(Dispatchers.Main){
            _empoloyee.value = current
        }

    }
      fun deleteCurrentUser(id : String ){
           val ref = employeeRef.document(id).delete()

    }

}