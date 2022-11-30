package com.cs4750.stocksentimenttracker.api

import retrofit2.Call
import retrofit2.http.GET


interface RedditApi {
    @GET("/r/wallstreetbets/.json")
    //return redditresponse model object which deserializes the JSON data to a Model Object
    fun fetchContents(): Call<RedditResponse>
}
