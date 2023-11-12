import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cs388.humanbenchmark.R

class ReflexGameActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflex_game)

        textView = findViewById(R.id.textView)
        textView.setOnClickListener {
            if (textView.text == getString(R.string.tap_to_start)) {
                startGame()
            } else {
                endGame()
            }
        }
    }

    private fun startGame() {
        // Change text and background color
        textView.text = getString(R.string.tap_when_white)
        textView.setBackgroundColor(getRandomColor())

        // Record the start time
        startTime = System.currentTimeMillis()

        // Set a timer to reset the color after a random time
        object : CountDownTimer((1000 + Math.random() * 3000).toLong(), 100) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                resetGame()
            }
        }.start()
    }

    private fun resetGame() {
        textView.text = getString(R.string.tap_to_start)
        textView.setBackgroundColor(Color.WHITE)
    }

    private fun endGame() {
        // Record the reaction time
        val reactionTime = System.currentTimeMillis() - startTime
        resetGame()
        // Do something with the reaction time (e.g., display it)
        // You can store it in a variable, log it, or display it to the user.
        println("Reaction Time: $reactionTime milliseconds")
    }

    private fun getRandomColor(): Int {
        val colors = arrayOf(
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA
        )
        return colors.random()
    }
}
