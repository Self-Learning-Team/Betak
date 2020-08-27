package com.example.betak.repository

import com.example.betak.model.entity.Employee
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class EmployeeInfoRepo {


companion object{

    //singleton class
    val instance : EmployeeInfoRepo by lazy {
        EmployeeInfoRepo()
    }

}

    var employeeRef = FirebaseFirestore.getInstance().collection("Employees")


  public  fun setEmployeeInformation(id : String, name: String, job: String, phone: String, whatsApp: String, area: String, governator: String) {

        var employee = Employee(id , name, job, phone, whatsApp, governator, area)

        GlobalScope.launch(Dispatchers.IO) {
            employeeRef.document(id).set(employee).await()

        }
    }








}