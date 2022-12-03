package com.cs4750.stocksentimenttracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val TAG = "LeaderboardFragment"
class LeaderboardFragment : Fragment() {

    private lateinit var stockListViewModel: StockListViewModel
    private lateinit var stockRecyclerView: RecyclerView

    private lateinit var photoRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        photoRecyclerView = view.findViewById(R.id.leaderboard_recycler_view)
        photoRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stockListViewModel.StockItemLiveData.observe(
            viewLifecycleOwner,
            Observer { galleryItems ->
                Log.d(TAG, "Have gallery items from ViewModel $galleryItems")
                // Eventually, update data backing the recycler view
            })
    }

    //creates an instance of a stream of data from the RedditFetchr Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //redditLiveData comes from background thread in the repo
        val redditLiveData: LiveData<List<Children>> = RedditFetchr().fetchContents()

//        //print stockItems
//        redditLiveData.observe(
//            this,
//            Observer { commentItems ->
//                Log.d(TAG, "Response received: $commentItems")
//            })
        stockListViewModel = ViewModelProviders.of(this).get(StockListViewModel::class.java)
    }

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
