package com.cs4750.stocksentimenttracker.api
import android.content.ContentValues.TAG
import android.util.Log
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import java.util.stream.Collectors.toList

class FinnhubApi {
    private lateinit var data : String
    fun fetchContents(ticker : String) {

        val thread = Thread {
            try {
                ApiClient.apiKey["token"] = "ce2kegiad3ia3rm8evs0ce2kegiad3ia3rm8evsg"
                val apiClient = DefaultApi()
                val trendData = apiClient.recommendationTrends(ticker)
                println(trendData.get(0))
                val data = trendData.get(0)

            } catch (e: Exception) {
                Log.e(TAG, "Could not fetch recommendation trends")
            }
        }
        thread.start()
    }
}