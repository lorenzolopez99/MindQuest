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

class AimTrainerGameActivity : AppCompatActivity() {
    private var positions = mutableListOf<Pair<Int, Int>>()
    private var gameTargets: Int = 30
    private var gamePlaying: Boolean = false
    private var startTime: Long = 0
    private var averageTime: Long = 0
    private var layoutParams = LayoutParams(
        LayoutParams.WRAP_CONTENT,
        LayoutParams.WRAP_CONTENT
    )

    private lateinit var startGameButton: Button
    private lateinit var titleText: TextView
    private lateinit var targetText: TextView
    private lateinit var scoreText: TextView
    private lateinit var gameContent: FrameLayout
    private lateinit var targetView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aim_trainer_activity)

        startGameButton = findViewById(R.id.aim_start_game_button)
        titleText = findViewById(R.id.aim_trainer_game_title)
        targetText = findViewById(R.id.aim_trainer_target_text)
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

    private fun startGame() {
        gamePlaying = true
        startGameButton.visibility = View.GONE
        titleText.visibility = View.GONE
        targetText.visibility = View.VISIBLE
        gameContent.visibility = View.VISIBLE
        Log.d("positions", "positions created = $positions")
        newTarget()
    }

    private fun newTarget() {
        startTime = System.currentTimeMillis()
        layoutParams.setMargins(kotlin.math.floor(Math.random() * 800).toInt(), kotlin.math.floor(
            Math.random() * 1300
        ).toInt(), 0, 0)
        targetView.layoutParams = layoutParams
    }
    private fun targetHit(id: Int) {
        if (!gamePlaying)
            return
        if (gameTargets == 0)
            gameOver()

        Log.d("target id", "target id = $id")
        if (gameTargets != 0) {
            Log.d("time elapsed", "${System.currentTimeMillis() - startTime}")
            averageTime += System.currentTimeMillis() - startTime
            gameTargets--
            targetText.text = "Targets: $gameTargets"
            newTarget()
        }
    }

    private fun gameOver() {
        Log.d("score", "Your score! ${averageTime/30}")
        scoreText.text = "Average Time Between Targets: ${averageTime/30} ms."
        gameTargets = 30
        averageTime = 0
        gamePlaying = false
        gameContent.removeAllViews()
        targetText.text = "Targets: 30"
        targetText.visibility = View.GONE
        gameContent.visibility = View.GONE
        titleText.visibility = View.VISIBLE
        scoreText.visibility = View.VISIBLE
        startGameButton.visibility = View.VISIBLE
    }
}