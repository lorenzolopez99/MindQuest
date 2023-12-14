package com.cs388.humanbenchmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HeaderAdapter(private val verticalData: List<Player>) : RecyclerView.Adapter<HeaderAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerNameView: TextView = itemView.findViewById(R.id.playerText)
        val rankView: TextView = itemView.findViewById(R.id.rankText)
        val scoreView: TextView = itemView.findViewById(R.id.scoreText)
        val gameTitleView: TextView = itemView.findViewById(R.id.gameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.leaderboard_header, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = verticalData[position]

        holder.rankView.text = "Rank"
        holder.scoreView.text = "Score"
        holder.playerNameView.text = "Player"

        if (player.game.toString() == "game1") {
            holder.gameTitleView.text = "Reaction Time Leaderboard"
        } else if (player.game.toString() == "game2") {
            holder.gameTitleView.text = "Memory Sequence Leaderboard"
        } else if (player.game.toString() == "game3") {
            holder.gameTitleView.text = "Aim Trainer Leaderboard"
        }
    }

    override fun getItemCount(): Int {
        return 1
    }
}