package com.example.betak.model.entity

import android.media.tv.TvView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.sql.Time
import java.util.*


class Chat() : Serializable {

    var receiver: String? = null
    var receiverImage: String? =null
    private var senderId: String? = null
    private var receiverId: String? = null
    var sender: String? = null
    var message: String? = null
    private var timestamp: Timestamp? = null
    private var seen : Boolean = false
    private var id : String? = null
    private var time : Date? =null
    private var currentTime : Long =0
    private var ok : Boolean = false


    constructor(receiver: String, receiverImage: String, senderId: String, receiverId: String
                , sender: String, message: String, timestamp: Timestamp ,
                seen : Boolean , id : String
                , time : Date , currentTime:Long , ok : Boolean) : this() {
        this.receiver = receiver
        this.receiverImage= receiverImage
        this.sender = sender
        this.senderId = senderId
        this.message = message
        this.timestamp = timestamp
        this.receiverId = receiverId
        this.seen = seen
        this.id = id
        this.time = time
        this.currentTime= currentTime
        this.ok = ok

    }

    fun getOk(): Boolean {
        return ok
    }

    fun setOk(ok: Boolean) {
        this.ok = ok
    }



    fun setCurrentTime(currentTime: Long){
        this.currentTime = currentTime
    }

    fun getCurrentTime(): Long{
        return currentTime!!
    }

    fun setTime(time : Date){
       this.time = time
    }

    fun getTime(): Date{
     return time!!
    }

    fun getSenderId(): String {
        return senderId!!
    }

    fun setSenderId(senderId: String) {
        this.senderId = senderId
    }


    fun getId(): String {
        return id!!
    }

    fun setId(id : String) {
        this.id = id
    }


    fun getReceiverId(): String {
        return receiverId!!
    }
    fun setReceiverId(receiverId: String ) {
        this.receiverId = receiverId
    }

    fun getSeen(): Boolean {
        return seen
    }

    fun setSeen(seen: Boolean) {
        this.seen = seen
    }

    fun getTimeStamp(): Timestamp? {
        return timestamp
    }
    fun setTimeStamp(timestamp: Timestamp ) {
        this.timestamp = timestamp
    }

}
