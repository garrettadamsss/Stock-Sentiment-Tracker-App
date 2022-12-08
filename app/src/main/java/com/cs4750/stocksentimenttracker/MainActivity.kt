package com.cs4750.stocksentimenttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

private val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, LeaderboardFragment.newInstance())
                .commit()
        }
    }

}