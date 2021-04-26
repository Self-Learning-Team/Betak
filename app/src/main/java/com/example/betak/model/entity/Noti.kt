package com.example.betak.model.entity

import java.io.Serializable

class Noti() : Serializable{

   var senderImage : String? = null
   var senderName: String? = null
   var time : Long =0
   var message : String? =null


    constructor(senderImage: String, senderName: String, time: Long, message: String):this() {
        this.senderImage = senderImage
        this.senderName = senderName
        this.time = time
        this.message = message

    }


}