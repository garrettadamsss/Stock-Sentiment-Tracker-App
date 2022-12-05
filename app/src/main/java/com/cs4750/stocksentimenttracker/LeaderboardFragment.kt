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
        val redditLiveData: LiveData<List<String>> = RedditFetchr().fetchContents()
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
            // vincent: implement a function to fetch new contents here
        }
    }

    private fun resetDatabase() {
        stockListViewModel.resetDatabase()
        Log.d(TAG, "Reset database")
    }



//    private class StockHolder(itemTextView: TextView)
//        : RecyclerView.ViewHolder(itemTextView) {
//        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
//    }
//


//    private class StockAdapter(private val stockItems: List<Stock>)
//        : RecyclerView.Adapter<StockHolder>() {
//
//        override fun onCreateViewHolder(
//            parent: ViewGroup,
//            viewType: Int
//        ): StockHolder {
//            val textView = TextView(parent.context)
//            return StockHolder(textView)
//        }
//
//        override fun getItemCount(): Int = stockItems.size
//        override fun onBindViewHolder(holder: StockHolder, position: Int) {
//            val stockItem = stockItems[position]
//            holder.bindTitle(stockItem.title)
//        }
//    }

    //    //sets up recycler view which asks adapter to create viewholders and bind data
//    private lateinit var leaderboardRecyclerView: RecyclerView
//    private var adapter: StockAdapter? = null
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)
//        leaderboardRecyclerView = view.findViewById(R.id.leaderboard_recycler_view)
//        leaderboardRecyclerView.layoutManager = LinearLayoutManager(context)
//        updateUI()
//        return view
//    }
//    //sets up UI for LeaderBoardFragment
//    private fun updateUI() {
//        val stocks = crimeListViewModel.crimes
//        adapter = CrimeAdapter(crimes)
//        crimeRecyclerView.adapter = adapter
//    }
//
//    //ViewHolder inner class: passes itemView to View which contains all of the views for an item
//    private inner class StockHolder(view: View) : RecyclerView.ViewHolder(view){
//        val tickerTextView: TextView = itemView.findViewById(R.id.stock_ticker)
//        val positionTextView: TextView = itemView.findViewById(R.id.stock_rank)
//    }
//    //Adapter inner class: creates viewholders and binds the data to viewholders
//    //pass in the list of stocks to be presented
//    private inner class StockAdapter(var stocks: List<StockItem>) : RecyclerView.Adapter<StockHolder>() {
//
//        //create viewholder
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
//            val view = layoutInflater.inflate(R.layout.list_item_stock, parent, false)
//            return StockHolder(view)
//        }
//        //bind data to view holder
//        override fun getItemCount() = stocks.size
//        override fun onBindViewHolder(holder: StockHolder, position: Int) {
//            val stock = stocks[position]
//            holder.apply {
//                tickerTextView.text = stock.ticker
//                positionTextView.text = stock.rank
//            }
//        }
//    }
    companion object {
        fun newInstance() = LeaderboardFragment()
    }
}
