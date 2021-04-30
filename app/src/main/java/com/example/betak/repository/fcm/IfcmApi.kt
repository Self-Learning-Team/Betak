package com.example.betak.repository.fcm

import com.example.betak.model.fcm.FcmResponse
import com.example.betak.model.fcm.FcmSendData
import retrofit2.Call
import retrofit2.http.*

interface IfcmApi {

    @Headers(*["Content-Type:application/json", "Authorization:key=AAAA1yqf7IY:APA91bHxyf_QLhjbcOlKQT5zydw5Z_pJQRV16ch_UnzVah3T6c_WxopI2S3cLKCLZIRWQi-TcSFbL4swbvrQmE91yF1OsAENb22-maDTbZwuMD1mZGAfRBbx7tWtjY-P1PFT2Lb65pRT"])
    @POST("fcm/send")
    fun sendNotification(@Body body: FcmSendData): Call<FcmResponse>


    @GET("place/queryautocomplete/json")
    fun getPlaces(@Query("input") input : String ,@Query("key") key : String )
}