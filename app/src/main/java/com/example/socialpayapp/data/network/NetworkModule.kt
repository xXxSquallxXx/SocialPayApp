package com.example.socialpayapp.data.network

import com.example.socialpayapp.data.network.api.SbpApi
import com.example.socialpayapp.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideSbpApi(retrofit: Retrofit): SbpApi {
        return retrofit.create(SbpApi::class.java)
    }
}