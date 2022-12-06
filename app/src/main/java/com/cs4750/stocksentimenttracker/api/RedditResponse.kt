package com.cs4750.stocksentimenttracker.api

import com.cs4750.stocksentimenttracker.Children
import com.cs4750.stocksentimenttracker.DataX
import com.google.gson.annotations.SerializedName

//maps to outermost object in JSON data and then maps to data key
class RedditResponse {
    @SerializedName("data")
    lateinit var data: ChildrenResponse
}