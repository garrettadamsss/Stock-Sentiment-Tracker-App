package com.cs4750.stocksentimenttracker

import java.util.*

//data class to hold all data about a Stock
data class Stock (val id: UUID = UUID.randomUUID(),
                  var ticker: String = "",
                  var rank: String = "",
                  var num_mentions: Int,
                  )