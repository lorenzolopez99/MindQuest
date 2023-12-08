package com.cs388.humanbenchmark

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.FrameLayout.LayoutParams

class AimTrainerGameActivity : AppCompatActivity() {
    var gameTargets: Int = 30
    var gamePlaying: Boolean = false

    lateinit var startGameButton: Button
    lateinit var titleText: TextView
    lateinit var scoreText: TextView
    lateinit var gameContent: FrameLayout
    lateinit var targetView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aim_trainer_activity)

        startGameButton = findViewById(R.id.aim_start_game_button)
        titleText = findViewById(R.id.aim_trainer_game_title)
        scoreText = findViewById(R.id.aim_trainer_score_text)
        gameContent = findViewById(R.id.aim_trainer_game_content)
        targetView = findViewById(R.id.target)

        startGameButton.setOnClickListener {
            startGame()
        }
    }

    fun startGame() {
        gamePlaying = true
        startGameButton.visibility = View.INVISIBLE
        titleText.visibility = View.INVISIBLE
        scoreText.visibility = View.VISIBLE
        gameContent.visibility = View.VISIBLE
        createPositions()
    }

    fun gameOver() {

    }

    fun createPositions() {
        var layoutParams = FrameLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.TOP or Gravity.START;
        layoutParams.setMargins(50, 100, 0, 0)

        targetView.setLayoutParams(layoutParams)
    }
}