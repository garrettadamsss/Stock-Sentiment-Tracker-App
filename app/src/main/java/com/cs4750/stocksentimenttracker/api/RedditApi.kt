package com.cs4750.stocksentimenttracker.api

import retrofit2.Call
import retrofit2.http.GET


interface RedditApi {
    @GET("/r/wallstreetbets/.json")
    fun fetchContents(): Call<String>
}
