package com.example.betak.model.entity

class place {

  var status : String
  var predictions : ArrayList<ListClass>

    constructor(status: String, predictionsData: ArrayList<ListClass>) {
        this.status = status
        this.predictions = predictionsData
    }
}