package com.example.betak.model.entity

import com.google.firebase.Timestamp
import java.io.Serializable


class Chat() : Serializable {

    var receiver: String? = null
    var senderId: String? = null
    var receiverId: String? = null
    var sender: String? = null
    var message: String? = null
    var timestamp: Timestamp? = null


    constructor(receiver: String, senderId: String, receiverId: String
                , sender: String, message: String, timestamp: Timestamp) : this() {
        this.receiver = receiver
        this.sender = sender
        this.senderId = senderId
        this.message = message
        this.timestamp = timestamp
        this.receiverId = receiverId
    }


}
