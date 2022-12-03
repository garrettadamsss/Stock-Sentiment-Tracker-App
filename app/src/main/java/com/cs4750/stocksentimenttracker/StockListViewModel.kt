package com.cs4750.stocksentimenttracker

import androidx.lifecycle.ViewModel

class StockListViewModel : ViewModel() {

    private val stockRepository = StockRepository.get()
    val stockList = stockRepository.getStocks()

    val topStocksLiveData = stockRepository.getTopStocks()

    fun incrementStock(ticker: String) {
        stockRepository.incrementStock(ticker)
    }
}