package com.example.betak.model.fcm

class FcmSendData() {

    private var to: String? = null
    private var data: Map<String , String>? = null

    constructor(to: String, data: Map<String , String>):this() {
        this.to = to
        this.data = data
    }

    fun getData(): Map<String , String>  {
        return data!!
    }

    fun setData(data: Map<String , String>) {
        this.data  = data
    }

    fun getTo(): String {
        return to!!
    }

    fun setTo(to: String) {
        this.to = to
    }

}