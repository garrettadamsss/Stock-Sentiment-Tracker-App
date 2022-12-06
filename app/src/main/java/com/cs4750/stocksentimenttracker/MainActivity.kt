package com.cs4750.stocksentimenttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cs4750.stocksentimenttracker.databinding.ActivityMainBinding

private val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


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


        // create instance of the ActivityMainBinding,
        // as we have only one layout activity_main.xml
        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // binding.root returns the root layout,
        // which is activity_main.xml file itself
        setContentView(binding.root)


    }
}

