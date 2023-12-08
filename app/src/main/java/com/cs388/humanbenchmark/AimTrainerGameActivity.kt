package com.cs388.humanbenchmark

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class AimTrainerGameActivity : AppCompatActivity() {
    var gameTargets: Int = 30
    var gamePlaying: Boolean = false

    lateinit var startGameButton: Button
    lateinit var titleText: TextView
    lateinit var scoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aim_trainer_activity)

        startGameButton = findViewById(R.id.aim_start_game_button)
        titleText = findViewById(R.id.aim_trainer_game_title)
        scoreText = findViewById(R.id.aim_trainer_score_text)

        startGameButton.setOnClickListener {
            startGameButton.visibility = View.INVISIBLE
            titleText.visibility = View.INVISIBLE
            scoreText.visibility = View.VISIBLE
            createPositions()
            startGame()
        }
    }

    fun startGame() {

    }

    fun gameOver() {

    }

    fun createPositions() {

    }
}