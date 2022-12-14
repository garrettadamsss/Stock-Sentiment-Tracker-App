package com.cs4750.stocksentimenttracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cs4750.stocksentimenttracker.api.ChildrenResponse
import com.cs4750.stocksentimenttracker.api.CommentResponse
import com.cs4750.stocksentimenttracker.api.RedditApi
import com.cs4750.stocksentimenttracker.api.RedditResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* Serves as repository class that encapsulates the logic for accessing data
* */
private const val TAG = "RedditFetchr"
class RedditFetchr {
    private val redditApi: RedditApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        redditApi = retrofit.create(RedditApi::class.java)
    }
    //calls thread to make request and receives list of stock items
    fun fetchContents(): LiveData<List<String>> {
        //LiveData is a communication to Fragments from the repo
        val responseLiveData: MutableLiveData<List<String>> = MutableLiveData()
        //make request
        val redditRequest: Call<RedditResponse> = redditApi.fetchContents()
        //run in background thread
        redditRequest.enqueue(object : Callback<RedditResponse> {
            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }
            override fun onResponse(
                call: Call<RedditResponse>,
                response: Response<RedditResponse>
            ) {
                Log.d(TAG, "Response received")
                //get response of outermost JSON object data
                val redditResponse: RedditResponse? = response.body()
                //get list of children
                val childrenResponse: ChildrenResponse? = redditResponse?.data

                //get list of children from childrenresponse
                var childrenItems: List<Children> = childrenResponse?.children
                    ?: mutableListOf()
                //create a list for dataX model objects
                val DataXItems: MutableList<DataX> = mutableListOf()
                //parse children list for datax
                childrenItems.forEach {
                    DataXItems.add(it.data)
                }
                //parse comments and extract body into Strings
                val comments: MutableList<String> = mutableListOf()
                DataXItems.forEach {
                    comments.add(it.body)
                }
                //set live data to list of stock items
                responseLiveData.value = comments
                Log.d(TAG, "size " + comments.size.toString())
            }
        })
        return responseLiveData
    }
}