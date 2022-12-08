package com.cs4750.stocksentimenttracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs4750.stocksentimenttracker.database.Stock

private const val TAG = "LeaderboardFragment"
class LeaderboardFragment : Fragment() {

    private val stockListViewModel : StockListViewModel by lazy {
        ViewModelProvider(this).get(StockListViewModel::class.java)
    }
    //sets up recycler view which asks adapter to create viewholders and bind data
    private lateinit var stockRecyclerView: RecyclerView
    private lateinit var refreshButton : Button
    private var adapter = StockAdapter(emptyList())

    //creates an instance of a stream of data from the RedditFetchr Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //redditLiveData comes from background thread in the repo
        /*the line of code below is what causes for contents to be fetched twice
        stocklistviewmodel already fetches content in init*/
        //val redditLiveData: LiveData<List<String>> = RedditFetchr().fetchContents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        stockRecyclerView = view.findViewById(R.id.leaderboard_recycler_view)
        refreshButton = view.findViewById(R.id.refresh_button)
        stockRecyclerView.layoutManager = LinearLayoutManager(context)
        stockRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateLeaderboard()
        refresh()
    }

    private fun updateUI(topStocks : List<Stock>) {
        adapter = StockAdapter(topStocks)
        stockRecyclerView.adapter = adapter
    }

    private fun countMentions(commentList: List<String>) {
        val stockList = stockListViewModel.stockList
        for(comment in commentList) {
            for(stock in stockList) {
                if(comment.contains(stock.ticker + ' ')) {  // at the beginning
                    stockListViewModel.incrementStock(stock.ticker)
                }
                else if(comment.contains(' ' + stock.ticker)) {  // at the end
                    stockListViewModel.incrementStock(stock.ticker)
                }
                else if(comment.contains(' ' + stock.ticker + ' ')) {  // at the middle
                    stockListViewModel.incrementStock(stock.ticker)
                  }
                else if(comment == stock.ticker) {   // only contains ticker
                    stockListViewModel.incrementStock(stock.ticker)
                }
            }
        }
    }

    private fun refresh() {

        refreshButton.setOnClickListener{
            resetDatabase()
            stockListViewModel.refresh()
            updateLeaderboard()
            // vincent: implement a function to fetch new contents here
        }
    }
    //I have to call updateLeaderboard twice, once in onviewcreate and refresh
    //it still works but its weird as there are two instances of observers doing the same thing
    //but removing one of the calls breaks the code, so for now I leave it
    private fun updateLeaderboard(){
        stockListViewModel.StockItemLiveData.observe(
            viewLifecycleOwner,
            Observer { stockItems ->
                //Log.d(TAG, "Have stock items from ViewModel $stockItems")
                Log.d(TAG, "Received new comments")
                // observe the changes in the list of top stocks
                countMentions(stockItems)
            }
        )
        stockListViewModel.topStocksLiveData.observe(
            viewLifecycleOwner,
            Observer { topStocks ->
                topStocks?.let {
                    Log.d(TAG, "Leaderboards updated")
                    updateUI(topStocks)
                }
            }
        )

    }

    private fun resetDatabase() {
        stockListViewModel.resetDatabase()
        Log.d(TAG, "Reset database")
    }

    companion object {
        fun newInstance() = LeaderboardFragment()
    }
}
