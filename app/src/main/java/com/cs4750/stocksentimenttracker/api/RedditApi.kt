package com.cs4750.stocksentimenttracker.api


import retrofit2.Call
import retrofit2.http.GET


//add here to set up and authorize reddit api
interface RedditApi {
    @GET("/")
    fun fetchContents(): Call<String>
}
