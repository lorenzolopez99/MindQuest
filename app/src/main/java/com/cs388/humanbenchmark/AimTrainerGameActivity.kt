package com.cs388.humanbenchmark

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.FrameLayout.LayoutParams
import java.lang.Math.floor

class AimTrainerGameActivity : AppCompatActivity() {
    var positions = mutableListOf<Pair<Int, Int>>()
    var gameTargets: Int = 30
    var gamePlaying: Boolean = false
    var layoutParams = LayoutParams(
        LayoutParams.WRAP_CONTENT,
        LayoutParams.WRAP_CONTENT
    )

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

        targetView.setOnClickListener {
            targetHit(targetView.id)
        }
    }

    fun startGame() {
        gamePlaying = true
        startGameButton.visibility = View.INVISIBLE
        titleText.visibility = View.INVISIBLE
        scoreText.visibility = View.VISIBLE
        gameContent.visibility = View.VISIBLE
        Log.d("positions", "positions created = $positions")
        newTarget()
    }

    fun newTarget() {
        layoutParams.setMargins(floor(Math.random() * 800).toInt(), floor(Math.random() * 1300).toInt(), 0, 0)
        targetView.setLayoutParams(layoutParams)
    }
    fun targetHit(id: Int) {
        if (!gamePlaying)
            return
        if (gameTargets == 0)
            gameOver()

        Log.d("target id", "target id = $id")
        if (gameTargets != 0) {
            gameTargets--
            scoreText.text = "Targets: ${gameTargets}"
            newTarget()
        }
    }

    fun gameOver() {

    }
}