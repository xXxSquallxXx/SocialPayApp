package com.example.socialpayapp.data.repository

import com.example.socialpayapp.data.db.dao.TransactionDao
import com.example.socialpayapp.data.db.entity.TransactionEntity
import com.example.socialpayapp.data.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty

class TransactionRepository(private val transactionDao: TransactionDao) {
    suspend fun insertTransaction(transaction: Transaction) {
        val entity = TransactionEntity(
            date = transaction.date,
            amount = transaction.amount,
            recipient = transaction.recipient
        )
        transactionDao.insert(entity)
    }

    fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions()
            .map { entities ->
                entities.map { entity ->
                    Transaction(
                        id = entity.id,
                        date = entity.date,
                        amount = entity.amount,
                        recipient = entity.recipient
                    )
                }
            }
            .onEmpty { emit(emptyList()) }
            .catch { emit(emptyList()) }
    }
}