package com.cs4750.stocksentimenttracker.api

import com.cs4750.stocksentimenttracker.StockItem
import com.google.gson.annotations.SerializedName

//automatically maps JSON array "comments" to the list of Stock data class
//inner object of JSON data
class CommentResponse{
    @SerializedName("comments")
    lateinit var stockItems: List<StockItem>
}