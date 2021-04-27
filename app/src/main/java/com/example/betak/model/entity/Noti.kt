package com.example.betak.model.entity

import java.io.Serializable

class Noti() : Serializable{

   var senderImage : String? = null
   var senderName: String? = null
   var time : Long =0
   var message : String? =null
   var senderId: String? = null
   private var open : Boolean? =null


    constructor(senderImage: String, senderName: String,
                time: Long, message: String, senderId: String , open : Boolean):this() {
        this.senderImage = senderImage
        this.senderName = senderName
        this.time = time
        this.message = message
        this.senderId = senderId
        this.open = open
    }

    fun getOpen(): Boolean {
        return open!!
    }

    fun setOpen(open: Boolean) {
        this.open = open
    }

}