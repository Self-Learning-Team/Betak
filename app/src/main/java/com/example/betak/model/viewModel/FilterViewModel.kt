package com.example.betak.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betak.model.entity.Employee
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FilterViewModel : ViewModel() {

    var _empoloyees = MutableLiveData<List<Employee>>()
    val employees : LiveData<List<Employee>>
        get()= _empoloyees

    var employeeRef = FirebaseFirestore.getInstance().collection("Employees")


    public fun filterEmployeeData(job: String , governator: String , area: String) {

        var employeeFilter : ArrayList<Employee> = ArrayList<Employee>()

        GlobalScope.launch (Dispatchers.IO){

            for (it in  employeeRef.whereEqualTo("job" , job).get().await().toObjects(Employee::class.java)){
                if (it.getArea().equals(area) && it.getGovernator().equals(governator)){
                    employeeFilter.add(it)
                }
            }
            withContext(Dispatchers.Main){
                _empoloyees.value = employeeFilter
            }
        }

    }
}