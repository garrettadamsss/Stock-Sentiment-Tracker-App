package com.cs4750.stocksentimenttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs4750.stocksentimenttracker.database.Stock


private val TAG = "MainActivity"

private val comments : List<String> = listOf(
    "TSLA",
    "TSLA",
    "TSLA",
    "TSLA",
    "TSLA",
    "TSLA",
    "TSLA",
    "TSLA",
    "TSLA",
    "AAPL",
    "AMD",
    "NVDA"
)

class MainActivity : AppCompatActivity() {

//    private val stockListViewModel : StockListViewModel by lazy {
//        ViewModelProvider(this).get(StockListViewModel::class.java)
//    }
//
//    private lateinit var refreshButton: Button
//    private lateinit var recyclerView : RecyclerView
//    private var adapter = StockAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, LeaderboardFragment.newInstance())
                .commit()
        }
    }// delete this later
//
//        refreshButton = findViewById(R.id.refreshBtn)
//        recyclerView = findViewById(R.id.recyclerview)
//
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // observe the changes in the list of top stocks
//        stockListViewModel.topStocksLiveData.observe(
//            this, Observer {
//                updateUI(it)    // update with new top stocks (leaderboard)
//            }
//        )
//
//        countMentions(comments)     // <-- List<String> from json will go here
//    }
//
//    private fun updateUI(topStocks : List<Stock>) {
//        adapter = StockAdapter(topStocks)
//        recyclerView.adapter = adapter
//    }
//
//    private fun countMentions(commentList: List<String>) {
//        refreshButton.setOnClickListener {
//            val stockList = stockListViewModel.stockList
//            for(comment in commentList) {
//                for(stock in stockList) {
//                    if(comment.contains(stock.ticker + ' ')) {  // at the beginning
//                        stockListViewModel.incrementStock(stock.ticker)
//                    }
//                    else if(comment.contains(' ' + stock.ticker)) {  // at the end
//                        stockListViewModel.incrementStock(stock.ticker)
//                    }
//                    else if(comment.contains(' ' + stock.ticker + ' ')) {  // at the middle
//                        stockListViewModel.incrementStock(stock.ticker)
//                    }
//                    else if(comment == stock.ticker) {   // only contains ticker
//                        stockListViewModel.incrementStock(stock.ticker)
//                    }
//                }
//            }
//        }
//    }

}
