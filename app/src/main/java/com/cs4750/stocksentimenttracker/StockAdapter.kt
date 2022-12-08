package com.cs4750.stocksentimenttracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cs4750.stocksentimenttracker.database.Stock

class StockAdapter(var topStocks: List<Stock>) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ticker : TextView = view.findViewById(R.id.tickerView)
        var count : TextView = view.findViewById(R.id.countView)
        var name : TextView = view.findViewById(R.id.nameView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_stock, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == 0){
            holder.ticker.text = "Ticker"
            holder.count.text = "Count"
            holder.name.text = "Stock Name"

        }else {
            val stock = topStocks[position-1]
            holder.ticker.text = stock.ticker
            holder.count.text = stock.count.toString()
            holder.name.text = stock.name

        }
    }

    override fun getItemCount(): Int {
        return topStocks.size
    }
}