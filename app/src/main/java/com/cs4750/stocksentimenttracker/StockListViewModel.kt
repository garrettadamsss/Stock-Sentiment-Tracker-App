package com.cs4750.stocksentimenttracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cs4750.stocksentimenttracker.api.FinnhubApi

class StockListViewModel : ViewModel() {

    val StockItemLiveData: LiveData<List<String>>

    init {
        StockItemLiveData = RedditFetchr().fetchContents()

    }

    private val stockRepository = StockRepository.get()
    val stockList = stockRepository.getStocks()
    val topStocksLiveData = stockRepository.getTopStocks()

    fun getFinnhubData()
    {
        var obj = FinnhubApi()
        var finnhubList : List<String>
        var stockList = topStocksLiveData.value
        if (stockList != null) {
            for(item in stockList) {
                if (stockList != null) {
                    // Prints all the top stock values to the terminal
                    System.out.println(obj.fetchContents(item.ticker).toString())
                }
            }
        }
    }

    fun incrementStock(ticker: String) {
        stockRepository.incrementStock(ticker)
    }
    fun resetDatabase() {
        stockRepository.resetDatabase()
    }
}