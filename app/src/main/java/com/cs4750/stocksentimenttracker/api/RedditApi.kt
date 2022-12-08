package com.cs4750.stocksentimenttracker.api

import com.cs4750.stocksentimenttracker.Children
import retrofit2.Call
import retrofit2.http.GET


interface RedditApi {
    @GET("/r/wallstreetbets/comments/.json?limit=1000")
    //return redditresponse model object which deserializes the JSON data to a Model Object
    fun fetchContents(): Call<RedditResponse>

}
