package com.cs388.humanbenchmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class LeaderboardFragment : Fragment() {
    lateinit var players: List<Player>
    lateinit var leaderboardRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        players = PlayerScoreFetcher.getScores()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leaderboardRv = view.findViewById(R.id.leaderBoardGame)
        val adapter = LeaderboardAdapter(players)

        leaderboardRv.adapter = adapter
        leaderboardRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //         create a horizontal recyclerview. Populate first layer (horizontal recycler) with an array
        //          of recyclerview objects.
        // sample code for horizontal layout :
        //         leaderboardRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}