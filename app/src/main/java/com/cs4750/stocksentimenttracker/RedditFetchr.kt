package com.cs4750.stocksentimenttracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cs4750.stocksentimenttracker.api.RedditApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
private const val TAG = "RedditFetchr"
class RedditFetchr {

    private val redditApi: RedditApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        redditApi = retrofit.create(RedditApi::class.java)
    }

    fun fetchContents(): LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val flickrRequest: Call<String> = redditApi.fetchContents()
        flickrRequest.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                Log.d(TAG, "Response received")
<<<<<<< Updated upstream
                responseLiveData.value = response.body()
=======
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
                Log.d(TAG, "first comment: "+comments[0])
>>>>>>> Stashed changes
            }
        })
        return responseLiveData
    }

}