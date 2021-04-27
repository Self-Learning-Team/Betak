package com.example.betak.model.fcm

import java.io.Serializable


class MyToken(): Serializable{

     private var token: String? = null

     constructor(token: String):this() {
        this.token = token
    }


    fun getToken(): String? {
        return token
    }

    fun setToken(token: String) {
        this.token = token
    }

}