package com.cs4750.stocksentimenttracker.api
import android.content.ContentValues.TAG
import android.util.Log
import com.cs4750.stocksentimenttracker.CommentItem
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient

class FinnhubApi {
    private lateinit var ticker : String
    private lateinit var buy : String
    private lateinit var sell : String
    private lateinit var strongBuy : String
    private lateinit var strongSell : String
    private lateinit var hold : String
    private lateinit var period : String
    // lateinit var commentItem :CommentItem
    private var data : String = " "
    fun fetchContents(ticker : String) {
        val thread = Thread {
            try {
                ApiClient.apiKey["token"] = "ce2kegiad3ia3rm8evs0ce2kegiad3ia3rm8evsg"
                val apiClient = DefaultApi()
                this.ticker = ticker
                var trendData = apiClient.recommendationTrends(ticker).get(0)
                println(trendData)
                // var commentItem : CommentItem? = CommentItem(ticker, trendData.buy.toString(),trendData.hold.toString(), trendData.period.toString(), trendData.sell.toString(), trendData.strongBuy.toString(), trendData.strongSell.toString())
            } catch (e: Exception) {
                Log.e(TAG, "Could not fetch recommendation trends")
            }
        }
        thread.start()
    }


    fun getBuy(): String
    {
        return "buy = " + buy
    }

    fun getStrongBuy(): String
    {
        return strongBuy
    }
    fun getHold(): String
    {
        return hold
    }
    fun returnPeriod(): String
    {
        return period
    }
    fun getSell(): String
    {
        return sell
    }
    fun getStrongSell(): String
    {
        return strongSell
    }
}