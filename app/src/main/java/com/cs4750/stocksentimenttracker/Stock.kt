package com.cs4750.stocksentimenttracker

data class Stock(
    val ticker: String,
    val name: String,
    var count : Int = 0
)