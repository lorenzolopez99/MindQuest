package com.cs388.humanbenchmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LeaderboardFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    lateinit var leaderboardHz: RecyclerView
    lateinit var game1Scores: MutableList<Player>
//    lateinit var game2Scores: List<Player>
//    lateinit var game3Scores: List<Player>
    lateinit var verticalDataList: MutableList<List<Player>> // List of data for each vertical RecyclerView


    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
        verticalDataList = ArrayList()
        game1Scores = ArrayList()

//        game1Scores = PlayerScoreFetcher.getScores("1")
//        game2Scores = PlayerScoreFetcher.getScores("2")
//        game3Scores = PlayerScoreFetcher.getScores("3")

//        Log.d("game1", "game1 : ${game1Scores} ")
//        Log.d("game1", "game2 : ${game2Scores} ")
//
//        Log.d("game1", "game3 : ${game3Scores} ")
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

        leaderboardHz = view.findViewById(R.id.leaderboardHz)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(leaderboardHz)

        val hzAdapter = LeaderboardHzAdapter(verticalDataList)
        leaderboardHz.adapter = hzAdapter
        leaderboardHz.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)



//        val array = FetchArray.getInstance()
//        verticalDataList = array.getArrays()
//        game1Scores = PlayerScoreFetcher.getScores("1")
//        game2Scores = PlayerScoreFetcher.getScores("2")
//        game3Scores = PlayerScoreFetcher.getScores("3")





    getUserData()




        //verticalDataList.removeFirst()
        // Create and set layout manager for the horizontal RecyclerView


    }


    fun getUserData() {
        val gameCategory = "1" // Change this to the appropriate game index

        val dbref = FirebaseDatabase.getInstance().getReference("leaderboard").child("game$gameCategory")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val players: MutableList<Player> = ArrayList()

                    for (userSnapshot in snapshot.children) {
                        val username = userSnapshot.child("username").getValue(String::class.java)
                        val score = userSnapshot.child("score").getValue(Int::class.java)

                        if (username != null && score != null) {
                            val player = Player(username, "game$gameCategory", 12)
                            players.add(player)
                        }
                    }

                    verticalDataList.add(players)
                    verticalDataList.add(players)
                    verticalDataList.add(players)

                    leaderboardHz.adapter = LeaderboardHzAdapter(verticalDataList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle cancellation
            }
        })
    }

}
