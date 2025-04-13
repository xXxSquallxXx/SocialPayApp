package com.example.socialpayapp

import android.app.Application
import com.example.socialpayapp.di.module.DaggerAppComponent

class App : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}