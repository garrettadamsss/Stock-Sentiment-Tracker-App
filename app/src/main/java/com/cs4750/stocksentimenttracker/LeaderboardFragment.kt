package com.cs4750.stocksentimenttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderboardFragment : Fragment() {
    private lateinit var photoRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        photoRecyclerView = view.findViewById(R.id.leaderboard_recycler_view)
        photoRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    companion object {
        fun newInstance() = LeaderboardFragment()
    }
}
