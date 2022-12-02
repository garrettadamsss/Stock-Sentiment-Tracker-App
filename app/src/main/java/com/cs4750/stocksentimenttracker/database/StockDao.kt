package com.cs4750.stocksentimenttracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface StockDao {
    @Update
    fun update(stock: Stock)

    @Query("SELECT * FROM stocks")
    fun getStocks() : List<Stock>

    @Query("SELECT * FROM stocks ORDER BY count DESC LIMIT 10")
    fun getTopStocks() : List<Stock>

    @Query("SELECT * FROM stocks WHERE ticker=(:ticker)")
    fun getStock(ticker: String) : LiveData<Stock?>
}