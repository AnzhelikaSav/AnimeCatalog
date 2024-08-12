package com.example.animecatalog.app

import android.app.Application
import com.example.animecatalog.di.DaggerAppComponent

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        val appComponent = DaggerAppComponent
            .create()
        DiProvider.appComponent = appComponent
    }
}