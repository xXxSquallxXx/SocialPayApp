package com.example.socialpayapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.socialpayapp.data.db.AppDatabase
import com.example.socialpayapp.data.db.dao.TransactionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return runBlocking(Dispatchers.IO) {
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            ).build()
        }
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: AppDatabase): TransactionDao {
        return database.transactionDao()
    }
}