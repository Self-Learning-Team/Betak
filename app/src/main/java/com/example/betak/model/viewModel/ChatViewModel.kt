package com.example.betak.model.viewModel

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betak.model.entity.Chat
import com.example.betak.model.entity.Employee
import com.example.betak.model.entity.Noti
import com.example.betak.model.fcm.FcmResponse
import com.example.betak.model.fcm.FcmSendData
import com.example.betak.model.fcm.MyToken
import com.example.betak.model.utils.Offline
import com.example.betak.repository.fcm.IfcmApi
import com.example.betak.repository.fcm.RetrofitClient
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class ChatViewModel : ViewModel() {


    init {
        Offline.setUp()
    }

    private val TAG = "ChatViewModel"

    var apiInterface = RetrofitClient.retrofit.create(IfcmApi::class.java)

    var tokenRef = Offline.db.collection("Tokens")
    var messageRef = Offline.db.collection("Messages")
    var employeeRef = Offline.db.collection("Employees")
    var notiRef = Offline.db.collection("Notifications")

    var mAuth = FirebaseAuth.getInstance()
    var uid = mAuth.currentUser!!.uid

    var _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>>
        get() = _chats

    var _empoloyee = MutableLiveData<Employee>()
    val employee: LiveData<Employee>
        get() = _empoloyee

    fun onlineUser(id: String, online: Boolean) {
        val map = mapOf("online" to online)

        GlobalScope.launch(Dispatchers.IO) {
            employeeRef.document(id).update(map).await()

        }
    }


    suspend fun addNewMessage(
        currentTime: Long,
        time: Date,
        senderId: String, receiverId: String
        , sender: String, receiver: String,
        message: String, timestamp: Timestamp
    ) {
        var sendTo = Employee()
        var currentUser = Employee()
        var token = MyToken()

        withContext(Dispatchers.IO) {
            val ref = employeeRef.document(receiverId).get().await()
            sendTo = ref.toObject(Employee::class.java)!!
        }

        withContext(Dispatchers.IO) {
            val ref = employeeRef.document(senderId).get().await()
            currentUser = ref.toObject(Employee::class.java)!!

        }

        withContext(Dispatchers.IO) {
            try {
                val ref = tokenRef.document(receiverId).get().await()
                token = ref.toObject(MyToken::class.java)!!
                Log.e("cor success", "success")

            } catch (e: Exception) {
                Log.e("cor error", e.message.toString())

            }
        }
        GlobalScope.launch {

            val doc =  messageRef.document()
            val chat = Chat(
                sendTo.getName()!!, sendTo.getImagePath() ,
                senderId, receiverId, sender, message, timestamp , false
                , doc.id , time , currentTime , false)

            doc.set(chat).addOnSuccessListener {
                Log.e(TAG, "success add message ")

            }.addOnFailureListener {
                Log.e(TAG, it.message.toString())
            }
        }
        if (sendTo.getOnline() == false) {
            saveNotification(receiverId, currentTime, sendTo, currentUser, senderId, message, token)
        }
        }




       fun saveNotification(
            receiverId: String,
            currentTime: Long,
            sendTo: Employee,
            curretUser: Employee,
            senderId: String,
            message: String,
            token: MyToken
    ) {
        val mes_noti = Noti(curretUser.getImagePath(),
                curretUser.getName()!!, currentTime, message, senderId, false)

        notiRef.document(receiverId).collection("noti")
                .document(currentTime.toString()).set(mes_noti).addOnSuccessListener {
                    Log.e("success noti", "success")
                }.addOnFailureListener {
                    Log.e("fail noti", it.message.toString())
                }


        val map = mapOf("title" to curretUser.getName()!!, "content" to message)

        val fcmSendData = FcmSendData(token.getToken()!!, map)

        apiInterface.sendNotification(fcmSendData).enqueue(object : Callback<FcmResponse> {
            override fun onFailure(call: Call<FcmResponse>, t: Throwable) {
                Log.e("error fcm", t.message.toString())
            }

            override fun onResponse(call: Call<FcmResponse>, response: Response<FcmResponse>) {
                Log.e("success fcm", response.body().toString())
            }
        })
    }


    suspend fun getCurrentUserInfo(id: String) {
        var current: Employee = Employee()

        withContext(Dispatchers.IO) {
            val ref = employeeRef.document(id).get().await()
            current = ref.toObject(Employee::class.java)!!
        }

        withContext(Dispatchers.Main) {
            _empoloyee.value = current
        }

    }


    suspend fun getAllMessages(id: String) {
        Log.e("shaima", "start get me messsages ")

        val arrayList: ArrayList<Chat> = ArrayList()

        withContext(Dispatchers.IO) {
           val getted =  messageRef.orderBy("timeStamp" , Query.Direction.ASCENDING).get().await()
                   getted.forEach {
                val chat = it.toObject(Chat::class.java)
                if((chat.getSenderId()==uid && chat.getReceiverId()==id) || (chat.getSenderId()==id && chat.getReceiverId()== uid)){
                    arrayList.add(chat)
                }
            }
        }
        withContext(Dispatchers.Main) {
            _chats.value = arrayList.reversed()
        }

    }

   suspend fun updateSeenMessages(id: String) {
        withContext(Dispatchers.IO) {
            val getted =  messageRef.get().await()
            getted.forEach {
                val docId = it.id
                val chat = it.toObject(Chat::class.java)
                 if(chat.getReceiverId().equals(uid) && chat.getSenderId().equals(id)){
                   val map = mapOf("seen" to true)
                   messageRef.document(docId).update(map)
                 }
            }
        }
    }

    suspend fun updateStartDateMessages() {
        val dates  : ArrayList<String> = ArrayList()

        withContext(Dispatchers.IO) {
            val getted =  messageRef.orderBy("timeStamp" , Query.Direction.ASCENDING).get().await()
            getted.forEach {
                val docId = it.id
                val chat = it.toObject(Chat::class.java)

          val date = DateUtils.getRelativeTimeSpanString(chat.getCurrentTime(),
          System.currentTimeMillis() , DateUtils.DAY_IN_MILLIS).toString()

                var map = mapOf("ok" to true)

                    if (!dates.contains(date) && chat.getOk()==false){

                        dates.add(date)
                        messageRef.document(docId).update(map)

                    }else{
                         map = mapOf("ok" to false)
                         messageRef.document(docId).update(map)

                    }

            }
        }
    }
}