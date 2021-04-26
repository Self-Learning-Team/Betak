package com.example.betak.repository.fcm

import com.example.betak.model.fcm.FcmResponse
import com.example.betak.model.fcm.FcmSendData
import retrofit2.Call
import retrofit2.http.*

interface IfcmApi {

    @Headers(*["Content-Type:application/json", "Authorization:key=AAAAjaBpksw:APA91bHrvW9kvWVC-N0-Zk89uDenPQNU2G06NZhWTrvbi7eXzj1-fjboJTJz_psh_hKTUiamy4s1HPfYYZ6p3omCl2Jnw8VvscMVSW7Bw7-9PTZ3qzP0xVOHK66Wx0lvAxnqNl-_EI-c"])
    @POST("fcm/send")
    fun sendNotification(@Body body: FcmSendData): Call<FcmResponse>


    @GET("place/queryautocomplete/json")
    fun getPlaces(@Query("input") input : String ,@Query("key") key : String )
}