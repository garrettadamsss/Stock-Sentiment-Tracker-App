package com.cs4750.stocksentimenttracker

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
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
        val stock = topStocks[position]
        holder.ticker.text = stock.ticker
        holder.count.text = stock.count.toString()
        holder.name.text = stock.name

    }

    override fun getItemCount(): Int {
        return topStocks.size
    }


    class RecyclerItemClickListener(
        context: Context?,
        recyclerView: RecyclerView,
        private val mListener: OnItemClickListener?
    ) :
        OnItemTouchListener {
        interface OnItemClickListener {
            fun onItemClick(view: View?, position: Int)
            fun onLongItemClick(view: View?, position: Int)
        }

        var mGestureDetector: GestureDetector

        init {
            mGestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(
                            child,
                            recyclerView.getChildAdapterPosition(child)
                        )
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
            val childView = view.findChildViewUnder(e.x, e.y)
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
                return true
            }
            return false
        }

        override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    }



}