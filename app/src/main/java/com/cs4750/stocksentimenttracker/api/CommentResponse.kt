package com.cs4750.stocksentimenttracker.api

import com.cs4750.stocksentimenttracker.CommentItem
import com.cs4750.stocksentimenttracker.DataX
import com.google.gson.annotations.SerializedName

//automatically maps JSON data to the Datax data class
//3rd level
class CommentResponse{
    @SerializedName("data")
    lateinit var commentItems: List<DataX>
}