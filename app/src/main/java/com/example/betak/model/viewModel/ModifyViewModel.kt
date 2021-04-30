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


class ModifyViewModel : ViewModel() {


    init {
        Offline.setUp()
    }

    var _empoloyee = MutableLiveData<Employee>()
    val employee  : LiveData<Employee>
     get()= _empoloyee

    var employeeRef = Offline.db.collection("Employees")

    fun completeModify( id : String , name : String, whats: String, phone: String, area: String, governator: String, job: String ) {

        val map = mapOf("id" to  id , "name" to name, "phone" to phone ,
        "whatsApp" to whats , "governator" to governator ,
                "area" to area , "job" to job )

        GlobalScope.launch(Dispatchers.IO) {
            employeeRef.document(id).update(map).await()

        }
    }

fun modifyImageUser(id: String , imagePath : String){
    val map = mapOf("imagePath" to imagePath)

    GlobalScope.launch(Dispatchers.IO) {
        employeeRef.document(id).update(map).await()

        withContext(Dispatchers.Main){
            _empoloyee.value = employeeRef.document(id).get().await().toObject(Employee::class.java)

        }
    }
}

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

}