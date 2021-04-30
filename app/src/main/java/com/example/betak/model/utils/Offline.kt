package com.example.betak.model.utils

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings




object Offline {

    var settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

    val  db = FirebaseFirestore.getInstance()

    fun setUp(){
      db.firestoreSettings = settings
    }


}