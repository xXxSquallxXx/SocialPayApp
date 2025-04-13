package com.example.socialpayapp.data.model

data class Transaction(
    val id: Long,
    val date: String,
    val amount: String,
    val recipient: String
)