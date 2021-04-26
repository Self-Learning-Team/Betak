package com.example.betak.model.entity

 class Rating(){

      var rate:Float?= null
      var opinion : String? = null
      var clientId: String? = null

     constructor(rate : Float , opinion: String , clientId: String):this(){
      this.clientId=clientId
      this.rate = rate
      this.opinion=opinion
     }
 }