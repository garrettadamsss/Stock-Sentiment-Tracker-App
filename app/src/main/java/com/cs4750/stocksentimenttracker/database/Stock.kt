package com.cs4750.stocksentimenttracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class Stock(
    @PrimaryKey val ticker: String,
    val name: String,
    var count : Int,
    // Stock Info
    var buy : Int,
    var hold : Int,
    var period : String,
    var sell : Int,
    var strongBuy : Int,
    var strongSell : Int
)


