package com.example.socialpayapp.di.module

import com.example.socialpayapp.data.network.NetworkModule
import com.example.socialpayapp.data.network.api.SbpApi
import com.example.socialpayapp.data.repository.TransactionRepository
import com.example.socialpayapp.domain.usecase.GetTransactionsUseCase
import com.example.socialpayapp.domain.usecase.ProcessPaymentUseCase
import com.example.socialpayapp.domain.usecase.ScanQrCodeUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ViewModelModule {
    @Provides
    @Singleton
    fun provideScanQrCodeUseCase(): ScanQrCodeUseCase {
        return ScanQrCodeUseCase()
    }

    @Provides
    @Singleton
    fun provideSbpApi(): SbpApi {
        return NetworkModule.provideSbpApi(NetworkModule.provideRetrofit())
    }

    @Provides
    @Singleton
    fun provideProcessPaymentUseCase(sbpApi: SbpApi): ProcessPaymentUseCase {
        return ProcessPaymentUseCase(sbpApi)
    }

    @Provides
    @Singleton
    fun provideGetTransactionsUseCase(repository: TransactionRepository): GetTransactionsUseCase {
        return GetTransactionsUseCase(repository)
    }
}