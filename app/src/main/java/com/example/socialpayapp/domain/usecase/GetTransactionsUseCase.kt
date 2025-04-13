package com.example.socialpayapp.domain.usecase

import com.example.socialpayapp.data.model.Transaction
import com.example.socialpayapp.data.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionsUseCase(private val repository: TransactionRepository) {
    fun execute(): Flow<List<Transaction>> {
        return repository.getAllTransactions()
    }
}