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

    // constructor
    // build database from asset
    private val database : StockDatabase = Room.databaseBuilder(
        context.applicationContext,
        StockDatabase::class.java,
        DATABASE_NAME
    ).createFromAsset("stocks.db")
        .allowMainThreadQueries()
        .build()

    private val stockDao = database.stockDao()
    private val executor = Executors.newSingleThreadExecutor()

    // methods
    fun getStocks() : List<Stock> = stockDao.getStocks()
    fun getTopStocks() : LiveData<List<Stock>> = stockDao.getTopStocks()
    fun incrementStock(ticker: String) = stockDao.incrementStock(ticker)
    fun resetDatabase() = stockDao.resetDatabase()

//    fun getStock(ticker: String) : LiveData<Stock?> = stockDao.getStock(ticker)
//    fun update(stock: Stock) {
//        executor.execute {
//            stockDao.update(stock)
//        }
//    }

    // singleton init
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