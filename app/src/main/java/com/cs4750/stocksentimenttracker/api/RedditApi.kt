package com.cs4750.stocksentimenttracker.api


import retrofit2.Call
import retrofit2.http.GET


//add here to set up and authorize reddit api
interface RedditApi {
    @GET("/r/wallstreetbets/comments/.json?limit=100")
    //return redditresponse model object which deserializes the JSON data to a Model Object
    fun fetchContents(): Call<RedditResponse>

}
