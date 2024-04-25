package com.example.projetoportugal

import android.app.Application
import com.example.projetoportugal.di.Injections
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(Injections.appModule)
        }
    }
}