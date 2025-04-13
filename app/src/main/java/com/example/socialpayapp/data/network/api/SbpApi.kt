package com.example.socialpayapp.data.network.api

import retrofit2.http.Body
import retrofit2.http.POST

interface SbpApi {
    @POST("payment")
    suspend fun processPayment(@Body request: PaymentRequest): PaymentResponse
}

data class PaymentRequest(
    val qrData: String,
    val amount: String,
    val recipient: String
)

data class PaymentResponse(
    val status: String,
    val transactionId: String
)