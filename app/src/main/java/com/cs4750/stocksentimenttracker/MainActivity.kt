package com.cs4750.stocksentimenttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.cs4750.stocksentimenttracker.database.Stock
import com.cs4750.stocksentimenttracker.database.StockDatabase

private lateinit var countText: TextView
private lateinit var refButton: Button

private val commentList : List<String> = listOf(
    "SPY is going up",
    "AAPL is going down",
    "AMD AMD AMD",
    "buy TSLA",
    "SPY",
    "MSFT",
    "GME"
)

class MainActivity : AppCompatActivity() {

    private var stockListViewModel = StockListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countText = findViewById(R.id.count_text)
        refButton = findViewById(R.id.refresh)

        val leaderboard = stockListViewModel.leaderboard

        val stockList = stockListViewModel.stockList
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

        // for debugging purposes
        var displaytext : String = ""
        for(stuff in leaderboard) {
            displaytext = displaytext + stuff.ticker + ' ' + stuff.count + '\n'
        }
        countText.text = displaytext
    }

    private fun refresh() {
        refButton.setOnClickListener {

        }
    }

}
