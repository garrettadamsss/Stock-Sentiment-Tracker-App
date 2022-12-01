package com.cs4750.stocksentimenttracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StockDao {
    @Update
    fun update(stock: Stock)

    @Query("SELECT * FROM stocks")
    fun getStocks() : List<Stock>

    @Query("SELECT * FROM stocks WHERE ticker=(:ticker)")
    fun getStock(ticker: String) : Stock?
}