package com.cs388.humanbenchmark

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cs388.humanbenchmark.R

class ReflexGameActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var startTime: Long = 0
    private var isGameRunning: Boolean = false
    private var isGameEnded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflex_game)

        textView = findViewById(R.id.tapToStart)
        textView.setOnClickListener {
            if (!isGameRunning && !isGameEnded) {
                startGame()
            } else if (textView.text == getString(R.string.tap_when_red) && isGameRunning) {
                endGameTooEarly()
            } else if (textView.text == getString(R.string.tap_when_green) && isGameRunning) {
                endGame()
            }
        }
    }

    private fun startGame() {
        // Change text and background color
        textView.text = getString(R.string.tap_when_red)
        textView.setBackgroundColor(Color.RED)
        isGameRunning = true
        isGameEnded = false

        // Set a timer to turn the screen green after a random time
        object : CountDownTimer((1000 + Math.random() * 3000).toLong(), 100) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                if (isGameRunning) {
                    turnScreenGreen()
                }
            }
        }.start()
    }

    private fun turnScreenGreen() {
        textView.text = getString(R.string.tap_when_green)
        textView.setBackgroundColor(Color.GREEN)
        startTime = System.currentTimeMillis()

        textView.setOnClickListener {
            if (isGameRunning && textView.text == getString(R.string.tap_when_green)) {
                endGame()
            } else if (isGameRunning && textView.text == getString(R.string.tap_when_red)) {
                endGameTooEarly()
            }
        }
    }

    private fun endGame() {
        // Check if the screen is green before processing
        if (isGameRunning && textView.text == getString(R.string.tap_when_green)) {
            // Record the reaction time
            val reactionTime = System.currentTimeMillis() - startTime
            // Display the reaction time
            textView.text = getString(R.string.reaction_time, reactionTime)
            isGameEnded = true

            textView.postDelayed({
                resetGame()
            }, 1000)
        }
    }

    private fun endGameTooEarly() {
        // Player clicked too early
        textView.text = getString(R.string.clicked_too_early)
        textView.setBackgroundColor(Color.RED)
        isGameRunning = false

        textView.postDelayed({
            resetGame()
        }, 1000)
    }

    private fun resetGame() {
        textView.text = getString(R.string.tap_to_start)
        textView.setBackgroundColor(Color.WHITE)
        isGameRunning = false
        isGameEnded = false // Reset the game-ended flag

        // Set the OnClickListener for "Tap to Start"
        textView.setOnClickListener {
            if (!isGameRunning && !isGameEnded) {
                startGame()
            } else if (textView.text == getString(R.string.tap_when_red) && isGameRunning) {
                endGameTooEarly()
            } else if (textView.text == getString(R.string.tap_when_green) && isGameRunning) {
                endGame()
            }
        }
    }

}
