package com.example.betak.model.utils

data class SmsRetrievedEvent(
    val isTimeout: Boolean,
    val smsMessage: String?
)
