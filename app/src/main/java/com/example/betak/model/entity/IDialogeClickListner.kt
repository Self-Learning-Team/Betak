package com.example.betak.model.entity

import android.content.DialogInterface


interface IDialogeClickListner {

    fun onClickPositiveButton(dialogInterface: DialogInterface, opinion: String, rating: Float)

    fun onClickNegativeButton(dialogInterface: DialogInterface)

}