package com.example.socialpayapp.di.module

import android.content.Context
import com.example.socialpayapp.data.network.NetworkModule
import com.example.socialpayapp.data.repository.TransactionRepository
import com.example.socialpayapp.domain.usecase.GetTransactionsUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {
    fun context(): Context
    fun getTransactionsUseCase(): GetTransactionsUseCase
    fun transactionRepository(): TransactionRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}