package com.example.betak.model.fcm

class FcmResponse {

    private var canonical_id: String? = null
    private var results: List<Result>? =null

     constructor(canonical_id: String, results: List<Result>) {
        this.canonical_id = canonical_id
        this.results = results
    }

    class Result {

        private val message_id: String

        constructor(message_id: String) {
            this.message_id = message_id
        }

    }
}