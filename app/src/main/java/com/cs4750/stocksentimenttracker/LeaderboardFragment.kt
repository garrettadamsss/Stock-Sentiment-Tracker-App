package com.cs4750.stocksentimenttracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val TAG = "LeaderboardFragment"
class LeaderboardFragment : Fragment() {

    //creates an instance of a stream of data from the RedditFetchr Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val redditLiveData: LiveData<String> = RedditFetchr().fetchContents()
        redditLiveData.observe(
            this,
            Observer { responseString ->
                Log.d(TAG, "Response received: $responseString")
            })
    }

    //sets up recycler view which asks adapter to create viewholders and bind data
    private lateinit var leaderboardRecyclerView: RecyclerView
    private var adapter: StockAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        leaderboardRecyclerView = view.findViewById(R.id.leaderboard_recycler_view)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }
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
//    private inner class StockAdapter(var stocks: List<Stock>) : RecyclerView.Adapter<StockHolder>() {
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
