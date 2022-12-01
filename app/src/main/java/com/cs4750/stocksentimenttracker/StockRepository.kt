package com.cs4750.stocksentimenttracker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.cs4750.stocksentimenttracker.database.Stock
import com.cs4750.stocksentimenttracker.database.StockDatabase
import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val DATABASE_NAME = "stock-database"

class StockRepository private constructor(context: Context) {

    private val database : StockDatabase = Room.databaseBuilder(
        context.applicationContext,
        StockDatabase::class.java,
        DATABASE_NAME
    ).createFromAsset("stocks.db")
        .allowMainThreadQueries()
        .build()

    private val stockDao = database.stockDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getStocks() : List<Stock> = stockDao.getStocks()
    fun getStock(ticker: String) : Stock? = stockDao.getStock(ticker)
    fun update(stock: Stock) {
        stockDao.update(stock)
//        executor.execute {
//
//        }
    }

    companion object {
        private var INSTANCE: StockRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = StockRepository(context)
            }
        }

        fun get(): StockRepository {
            return INSTANCE ?:
            throw IllegalStateException("StockRepository must be initialized")
        }
    }
}