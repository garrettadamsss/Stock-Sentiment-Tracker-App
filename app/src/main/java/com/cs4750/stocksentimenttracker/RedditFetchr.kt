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
    //calls thread to make request and recieves list of stock items
//    fun fetchContents(): LiveData<List<CommentItem>> {
//        //LiveData is a communication to Fragments from the repo
//        val responseLiveData: MutableLiveData<List<CommentItem>> = MutableLiveData()
//        //make request
//        val redditRequest: Call<RedditResponse> = redditApi.fetchContents()
//        //run in background thread
//        redditRequest.enqueue(object : Callback<RedditResponse> {
//            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
//                Log.e(TAG, "Failed to fetch photos", t)
//            }
//            override fun onResponse(
//                call: Call<RedditResponse>,
//                response: Response<RedditResponse>
//            ) {
//                Log.d(TAG, "Response received")
//                //get response of outermost JSON object
//                val redditResponse: RedditResponse? = response.body()
//                //get list of comments from redditResponse
//                val commentResponse: CommentResponse? = redditResponse?.comments
//                //get stock items list from commentResponse
//                var commentItems: List<CommentItem> = commentResponse?.commentItems
//                    ?: mutableListOf()
//                //filters out null url response
////                stockItems = stockItems.filterNot {
////                    it.url.isBlank()
////                }
//                //set live data to list of stock items
//                responseLiveData.value = commentItems
//            }
//        })
//        return responseLiveData
//    }
    fun fetchContents(): LiveData<List<Children>> {
        //LiveData is a communication to Fragments from the repo
        val responseLiveData: MutableLiveData<List<Children>> = MutableLiveData()
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


//                //get list of data within children
//                val commentResponse: CommentResponse? = childrenResponse?.children
                //get comments list from commentResponse
//                var commentItems: List<DataX> = commentResponse?.commentItems
//                    ?: mutableListOf()
                var commentItems: List<Children> = childrenResponse?.children
                    ?: mutableListOf()
//                var comments: List<DataX> = commentItems.DataX
                //set live data to list of stock items
                responseLiveData.value = commentItems
            }
        })
        return responseLiveData
    }
}