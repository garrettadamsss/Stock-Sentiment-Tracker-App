package com.cs4750.stocksentimenttracker

import android.util.Log
import com.cs4750.stocksentimenttracker.database.Stock


class StocksLeaderboard(commentList : List<String>, stockListViewModel: StockListViewModel) {
    private val leaderboards = mutableListOf<Stock>()
    private val stockList = stockListViewModel.stockList
    init {
        for(comment in commentList) {
            for(stock in stockList) {
                val updatedStock = stock.copy(count = stock.count + 1)
                if(comment.contains(stock.ticker + ' ')) {  // at the beginning
                    stockListViewModel.updateStock(updatedStock)
                }
                else if(comment.contains(' ' + stock.ticker)) {  // at the end
                    stockListViewModel.updateStock(updatedStock)
                }
                else if(comment.contains(' ' + stock.ticker + ' ')) {  // at the middle
                    stockListViewModel.updateStock(updatedStock)
                }
                else if(comment == stock.ticker) {   // only contains ticker
                    stockListViewModel.updateStock(updatedStock)
                }
            }
        }
        // get the top 10
        val tempList = stockList.toMutableList()
        repeat(10) {
            var topStock = tempList.maxByOrNull{it.count}
            leaderboards.add(topStock!!)
            tempList.remove(topStock)
        }
    }
    fun getLeaderboard() : MutableList<Stock> {
        return leaderboards
    }
}