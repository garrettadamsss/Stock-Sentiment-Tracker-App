package com.cs4750.stocksentimenttracker

import com.google.gson.annotations.SerializedName
import java.util.*

//data class to hold all data about a Stock
data class CommentItem (val id: UUID = UUID.randomUUID(),
                        var data: String = "",
                  )