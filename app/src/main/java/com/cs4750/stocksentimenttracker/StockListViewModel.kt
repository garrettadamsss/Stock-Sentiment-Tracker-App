package com.cs4750.stocksentimenttracker

import android.util.Log
import com.cs4750.stocksentimenttracker.database.Stock

class StockListViewModel {

    private val stockRepository = StockRepository.get()
    val stockList = stockRepository.getStocks()

    fun updateStock(stock: Stock) {
        stockRepository.update(stock)
    }
}