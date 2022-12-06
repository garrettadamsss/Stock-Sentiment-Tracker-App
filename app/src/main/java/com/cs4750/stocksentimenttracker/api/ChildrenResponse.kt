package com.cs4750.stocksentimenttracker.api

import com.cs4750.stocksentimenttracker.Children
import com.cs4750.stocksentimenttracker.DataX
import com.google.gson.annotations.SerializedName

//maps to the Json children to children dataclass
//2nd level
class ChildrenResponse {
    @SerializedName("children")
    lateinit var children: List<Children>
}