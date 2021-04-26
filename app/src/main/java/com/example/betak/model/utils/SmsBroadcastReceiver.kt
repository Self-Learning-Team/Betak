package com.example.betak.model.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import org.greenrobot.eventbus.EventBus
import java.util.regex.Matcher
import java.util.regex.Pattern


class SmsBroadcastReceiver : BroadcastReceiver() {

    var SMS_RECIEVED = "android.provider.Telephony.SMS_RECEIVED"

    companion object {
        val TAG = SmsBroadcastReceiver::class.java.simpleName
    }

    var  p  = Pattern.compile("(|^)\\d{6}");

    override fun onReceive(context: Context?, intent: Intent) {

        val data = intent.extras
        val pdus = data!!["pdus"] as Array<Any>?
        for (i in pdus!!.indices) {
            val smsMessage = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
            val sender = smsMessage.displayOriginatingAddress
            val phoneNumber = smsMessage.displayOriginatingAddress
            val senderNum = phoneNumber
            val messageBody = smsMessage.messageBody
            try {
                if (messageBody != null) {
                    val m: Matcher = p.matcher(messageBody)
                    if (m.find()) {
                        val otp = m.group(0).split(":")[0].trim()
                        ///event bus
                        EventBus.getDefault().postSticky(Event(otp = otp))
                        Log.e("sms message recieved" , otp)
                    }
                }
            } catch (e: Exception) {
                Log.e("sms exception recieved :" , e.toString())

            }
        }
        }

    }