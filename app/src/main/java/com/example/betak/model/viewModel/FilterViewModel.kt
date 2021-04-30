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

class FilterViewModel : ViewModel() {

    init {
        Offline.setUp()
    }

    var _empoloyees = MutableLiveData<List<Employee>>()
    val employees : LiveData<List<Employee>>
    get()= _empoloyees

    var employeeRef = Offline.db.collection("Employees")


    public fun filterEmployeeData(job: String , governator: String , area: String) {

        var employeeFilter : ArrayList<Employee> = ArrayList<Employee>()

        GlobalScope.launch (Dispatchers.IO){

            for (it in  employeeRef.get().await()){

                var employee = it.toObject(Employee::class.java)
                if (employee.getArea().equals(area) &&
                    employee.getGovernator().equals(governator) && employee.getJob().equals(job)){
                    employeeFilter.add(employee)
                }
            }
            withContext(Dispatchers.Main){
                _empoloyees.value = employeeFilter
            }
        }

    }
}