package com.cs4750.stocksentimenttracker

import android.app.Application

class StockIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        StockRepository.initialize(this)
    }
}