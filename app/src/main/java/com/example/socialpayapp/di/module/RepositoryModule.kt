package com.example.socialpayapp.di.module

import com.example.socialpayapp.data.db.dao.TransactionDao
import com.example.socialpayapp.data.repository.TransactionRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @Provides
    fun provideTransactionRepository(transactionDao: TransactionDao): TransactionRepository {
        return TransactionRepository(transactionDao)
    }
}