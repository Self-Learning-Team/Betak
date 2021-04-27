package com.example.betak.model.fcm

import com.example.betak.model.fcm.FcmCommon.showNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*


class FcmService : FirebaseMessagingService() {


    override fun onNewToken(newToken : String) {
        super.onNewToken(newToken)
        FcmCommon.updateToken(newToken);
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data != null) {

            if (remoteMessage.data[FcmCommon.TITLE_KEY] != null &&
                    remoteMessage.data[FcmCommon.CONTENT_KEY] != null) {
                showNotification(this, Random().nextInt()
                        , remoteMessage.data[FcmCommon.TITLE_KEY]
                        , remoteMessage.data[FcmCommon.CONTENT_KEY]
                        , null)
            }
        }
    }


}