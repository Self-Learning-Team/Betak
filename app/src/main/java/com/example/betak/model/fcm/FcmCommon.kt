package com.example.betak.model.fcm

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.text.TextUtils
import androidx.core.app.NotificationCompat
import com.example.betak.R
import com.google.android.gms.common.internal.service.Common
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


object FcmCommon {

    val TITLE_KEY = "title"
    val CONTENT_KEY = "content"


    @SuppressLint("ObsoleteSdkInt")
    fun showNotification(
            context: Context
            , noti_id: Int, title: String?, content: String?, intent: Intent?
    ) {
        var pendingIntent: PendingIntent? = null
        if (intent != null) {
            pendingIntent = PendingIntent.getActivity(
                    context
                    , noti_id, intent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        val notification_channel_id = "my_noti_channel_01"
        val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    notification_channel_id,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationChannel.description = "Channel Discription"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            val builder: NotificationCompat.Builder =
                    NotificationCompat.Builder(context, notification_channel_id)
            builder.setContentTitle(title)
                    .setContentText(content)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.employee)
                    .setLargeIcon(
                            BitmapFactory.decodeResource(
                                    context.getResources(),
                                    R.mipmap.employee
                            )
                    ).setDefaults(Notification.DEFAULT_VIBRATE)
                    .setDefaults(Notification.DEFAULT_SOUND)
            if (pendingIntent != null) builder.setContentIntent(pendingIntent)
            val mNotification: Notification = builder.build()
            notificationManager.notify(noti_id, mNotification)
        }
    }

    fun updateToken(s: String) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser

        val myToken = MyToken(s)

        if (firebaseUser!!.uid != null) {
            GlobalScope.launch {

                FirebaseFirestore.getInstance().collection("Tokens")
                        .document(firebaseUser!!.uid)
                        .set(myToken)
                        .addOnSuccessListener {

                        }
            }
        }

    }
}

