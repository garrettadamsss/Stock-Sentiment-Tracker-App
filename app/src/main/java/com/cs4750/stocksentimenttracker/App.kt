package com.cs4750.stocksentimenttracker

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        StockRepository.initialize(this)
    }
}
