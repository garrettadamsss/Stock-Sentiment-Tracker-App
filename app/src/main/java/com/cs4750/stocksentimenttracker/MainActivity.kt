package com.cs4750.stocksentimenttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.cs4750.stocksentimenttracker.database.Stock
import com.cs4750.stocksentimenttracker.database.StockDatabase

private lateinit var countText: TextView

private val commentList : List<String> = listOf(
    "SPY is going up",
    "AAPL is going down",
    "AMD AMD AMD",
    "buy TSLA",
    "SPY",
    "MSFT"
)

class MainActivity : AppCompatActivity() {
    private var leaderboard = mutableListOf<Stock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stockListViewModel = StockListViewModel()
        leaderboard = StocksLeaderboard(commentList, stockListViewModel).getLeaderboard()

        // for debugging purposes
        countText = findViewById(R.id.count_text)

        var displaytext : String = ""
        for(stuff in leaderboard) {
            displaytext = displaytext + stuff.ticker + ' ' + stuff.count + '\n'
        }
        countText.text = displaytext
    }

}
