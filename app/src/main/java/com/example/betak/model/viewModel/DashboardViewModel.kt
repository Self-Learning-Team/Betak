package com.example.betak.model.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betak.model.entity.Chat
import com.example.betak.model.entity.Employee
import com.example.betak.model.entity.Noti
import com.example.betak.model.utils.Offline
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DashboardViewModel : ViewModel() {

    init {
        Offline.setUp()
    }

    var notiRef = Offline.db.collection("Notifications")
    var employeeRef = Offline.db.collection("Employees")

    var _notes = MutableLiveData<List<Noti>>()
    val notes: LiveData<List<Noti>>
    get() = _notes

    var _open = MutableLiveData<List<Noti>>()
    val open: LiveData<List<Noti>>
        get() = _open

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

    suspend fun getNotification(id : String){
        Log.e("shaima" , "start get notes ")

        val arrayList : ArrayList<Noti> = ArrayList()

        withContext(Dispatchers.IO){
            val notes = notiRef.document(id).collection("noti")
            notes.get().await().forEach {
                val note = it.toObject(Noti::class.java)
                arrayList.add(note)
            }
        }
        withContext(Dispatchers.Main){
            _notes.value = arrayList.reversed()
        }
    }

    suspend fun getNonOpenNotification(id : String ){
        Log.e("shaima" , "start get notes ")

        val arrayList : ArrayList<Noti> = ArrayList()

        withContext(Dispatchers.IO){
            val notes = notiRef.document(id).collection("noti")
            notes.get().await().forEach {
                val note = it.toObject(Noti::class.java)
                if(note.getOpen()==false)  arrayList.add(note)
            }
        }
        withContext(Dispatchers.Main){
            _open.value = arrayList
        }
    }

    fun onAppUser(id: String, onApp: Boolean) {
        val map = mapOf("onApp" to onApp)

        GlobalScope.launch(Dispatchers.IO) {
            employeeRef.document(id).update(map).await()

        }
    }

}
