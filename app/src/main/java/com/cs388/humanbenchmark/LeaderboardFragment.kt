package com.cs388.humanbenchmark

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearSnapHelper



class LeaderboardFragment : Fragment() {
    lateinit var game1Scores: List<Player>
    lateinit var game2Scores: List<Player>
    lateinit var game3Scores: List<Player>
    lateinit var verticalDataList: MutableList<List<Player>> // List of data for each vertical RecyclerView
    lateinit var leaderboardRv: RecyclerView
    lateinit var leaderboardHz: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game1Scores = PlayerScoreFetcher.getScores("1")
        game2Scores = PlayerScoreFetcher.getScores("2")
        game3Scores = PlayerScoreFetcher.getScores("3")

        Log.d("game1", "game1 : ${game1Scores} ")
        Log.d("game1", "game2 : ${game2Scores} ")

        Log.d("game1", "game3 : ${game3Scores} ")
        // Populate verticalDataList with data for each vertical RecyclerView
        // You can decide how to split 'players' into three parts, one for each vertical RecyclerView
        // For example, if players.size = 30, you might want to split it into three lists of 10 players each.
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


        verticalDataList = ArrayList()
        val array = FetchArray.getInstance()
        verticalDataList = array.getArrays()


//        verticalDataList.add(game1Scores) // game 1 for exmpl
//        verticalDataList.add(game2Scores) // game 2
//        verticalDataList.add(game3Scores) // game 3



        //verticalDataList.removeFirst()
        // Create and set layout manager for the horizontal RecyclerView
        leaderboardHz = view.findViewById(R.id.leaderboardHz)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(leaderboardHz)

        val hzAdapter = LeaderboardHzAdapter(verticalDataList)
        leaderboardHz.adapter = hzAdapter
        leaderboardHz.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }
}
