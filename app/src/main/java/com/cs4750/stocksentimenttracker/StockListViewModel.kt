package com.cs4750.stocksentimenttracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4750.stocksentimenttracker.database.Stock

class StockListViewModel {

    private val stockRepository = StockRepository.get()
    val stockList = stockRepository.getStocks()
    val leaderboard = stockRepository.getTopStocks()

    fun updateStock(stock: Stock) {
        stockRepository.update(stock)
    }
}