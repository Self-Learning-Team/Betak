package com.example.betak.model.entity

import java.io.Serializable

class Employee() : Serializable {

    private var id : String? = null
    private var name: String? =null
    private var imagePath: String? =null
    private var job: String? =null
    private var phone: String? = null
    private var whatsApp: String? =null
    private var governator: String? =null
    private var area: String? = null
    private var online : Boolean = false

 /*   constructor( name: String , imagePath:String):this(){
        this.name =name
        this.imagePath = imagePath
    }*/
 constructor(id:String , name: String , job: String , phone: String , whatsApp: String ,
             governator: String , area: String , imagePath:String , online: Boolean):this(){
     this.area=area
     this.name =name
     this.governator=governator
     this.phone=phone
     this.whatsApp=whatsApp
     this.id=id
     this.job=job
     this.imagePath = imagePath
     this.online = online
 }


    fun getId(): String? {
        return id
    }

    fun setId(id : String?) {
        this.id= id!!
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name!!
    }

    fun getJob(): String {
        return job!!
    }

    fun setJob(job: String) {
        this.job = job
    }

    fun getPhone(): String {
        return phone!!
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun getWhatsApp(): String {
        return whatsApp!!
    }

    fun setWhatsApp(whatsApp: String) {
        this.whatsApp = whatsApp
    }

    fun getGovernator(): String {
        return governator!!
    }

    fun setGovernator(governator: String) {
        this.governator = governator
    }

    fun getArea(): String {
        return area!!
    }

    fun setArea(area: String) {
        this.area = area
    }
    fun getImagePath(): String {
        return imagePath!!
    }

    fun setImagePath(imagePath: String) {
        this.imagePath = imagePath
    }
    fun getOnline(): Boolean {
        return online
    }

    fun setOnline(online: Boolean) {
        this.online = online
    }
}