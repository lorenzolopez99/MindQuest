package com.cs388.humanbenchmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var players: List<Player>

class LeaderboardFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val leaderboardRv = view?.findViewById<RecyclerView>(R.id.leaderBoardGame)
        players = PlayerScoreFetcher.getScores()
        val adapter = LeaderboardAdapter(players)
        if (leaderboardRv != null) {
            leaderboardRv.adapter = adapter
        }
        if (leaderboardRv != null) {
            leaderboardRv.layoutManager = LinearLayoutManager(context)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)

    }

}