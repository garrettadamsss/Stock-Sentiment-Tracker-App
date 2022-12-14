package com.cs4750.stocksentimenttracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class StockListViewModel : ViewModel() {
    //changed to var so that I can change when refreshed
    var StockItemLiveData: LiveData<List<String>>

    init {
        StockItemLiveData = RedditFetchr().fetchContents()
    }

    private val stockRepository = StockRepository.get()
    val stockList = stockRepository.getStocks()
    val topStocksLiveData = stockRepository.getTopStocks()

    fun incrementStock(ticker: String) {
        stockRepository.incrementStock(ticker)
    }
    fun resetDatabase() {
        stockRepository.resetDatabase()
    }
    fun refresh() {
        StockItemLiveData= RedditFetchr().fetchContents()
    }
}