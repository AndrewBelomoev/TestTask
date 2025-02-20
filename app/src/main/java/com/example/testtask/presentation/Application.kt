package com.example.testtask.presentation

import android.app.Application
import com.example.data.di.dataModule
import com.example.testtask.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                dataModule,
                viewModelModule
            )
        }

    }
}