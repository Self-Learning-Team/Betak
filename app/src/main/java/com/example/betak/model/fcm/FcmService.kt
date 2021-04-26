package com.example.betak.model.fcm

import android.content.ServiceConnection
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService : FirebaseMessagingService() {


    override fun onNewToken(newToken : String) {
        super.onNewToken(newToken)
        FcmCommon.updateToken(newToken);
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

    }


}