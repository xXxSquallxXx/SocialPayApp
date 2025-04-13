package com.example.socialpayapp.domain.usecase

import com.example.socialpayapp.data.network.api.SbpApi
import com.example.socialpayapp.data.network.api.PaymentRequest

class ProcessPaymentUseCase(private val sbpApi: SbpApi) {
    suspend fun execute(qrData: String, amount: String, recipient: String) {
        val request = PaymentRequest(qrData, amount, recipient)
        sbpApi.processPayment(request)
    }
}