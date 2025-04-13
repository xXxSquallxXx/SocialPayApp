package com.example.socialpayapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.socialpayapp.data.db.dao.TransactionDao
import com.example.socialpayapp.data.db.entity.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        const val DATABASE_NAME = "socialpayapp_db"
    }
}