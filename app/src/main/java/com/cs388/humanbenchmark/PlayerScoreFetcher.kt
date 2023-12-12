package com.cs388.humanbenchmark
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class PlayerScoreFetcher {

    companion object {

        fun getScores(index:String): MutableList<Player> {
            var database : DatabaseReference = Firebase.database.reference
            var players: MutableList<Player> = ArrayList()
            database.child("leaderboard").child("game$index").get().addOnCompleteListener {
                if (it.isSuccessful){
                    val result = it.result.value as Map<String,Int>
                    for (player in result.keys){
                        val player = Player(player, "game$index", result[player]!!)
                        players.add(player)
                    }
                    //Log.d("GOON",player["Thibault Chezaud"].toString())
                }
                else{
                    Log.d("GOON",it.exception?.message.toString())
                }
            }
            return players
        }
    }
}