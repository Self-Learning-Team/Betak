package com.example.betak.model.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.betak.model.entity.Employee
import com.example.betak.repository.EmployeeInfoRepo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class EmployeeInfoViewModel(application: Application) : BaseViewModel(application) {

    var employeeRef = FirebaseFirestore.getInstance().collection("Employees")


     var _empoloyees = MutableLiveData<List<Employee>>()
     val employees : LiveData<List<Employee>>
     get()= _empoloyees

    var _filter= MutableLiveData<List<Employee>>()
    val filter : LiveData<List<Employee>>
    get()= _filter


    fun setEmployeeInfo(id : String , name: String, job: String, phone: String, whatsApp: String, area: String, governator: String , image: String , online : Boolean){
       EmployeeInfoRepo.instance.setEmployeeInformation(id , name , job, phone, whatsApp, area, governator , image , online)
    }


    fun getEmployeeInfo(job : String){

            var employeeFilter : ArrayList<Employee> = ArrayList<Employee>()

            GlobalScope.launch (Dispatchers.IO){

                for (it in  employeeRef.whereEqualTo("job" , job).get().await().toObjects(Employee::class.java)){
                    employeeFilter.add(it)
                }
           retieveData(employeeFilter)
            }
    }

    private suspend fun retieveData(employeeFilter: ArrayList<Employee>) {

     withContext(Dispatchers.Main){
        _empoloyees.value = employeeFilter
     }
    }


}