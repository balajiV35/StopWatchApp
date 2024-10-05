package com.example.stopwatchapp


import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var pauseAt: Long = 0
    private lateinit var btnStart: Button
    private lateinit var btnReset: Button
    private lateinit var btnPause: Button
    private lateinit var stopWatch: Chronometer
    private var isPaused: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        btnReset = findViewById(R.id.btnReset)
        btnPause = findViewById(R.id.btnPause)
        stopWatch = findViewById(R.id.stopWatch)

        btnStart.setOnClickListener {
            if (!isPaused) {
                stopWatch.base = SystemClock.elapsedRealtime()
            } else {
                stopWatch.base = SystemClock.elapsedRealtime() - pauseAt
                stopWatch.start()
                isPaused = false
                btnPause.text = "Pause"
            }
            stopWatch.start()

            btnPause.visibility = View.VISIBLE
            btnPause.isEnabled = true

            btnReset.visibility = View.VISIBLE
            btnReset.isEnabled = true

            btnStart.visibility = View.INVISIBLE
            btnStart.isEnabled = false
        }

        btnReset.setOnClickListener {
            stopWatch.base = SystemClock.elapsedRealtime()

            btnPause.visibility = View.INVISIBLE
            btnPause.isEnabled = false

            btnReset.visibility = View.INVISIBLE
            btnReset.isEnabled = false

            btnStart.visibility = View.VISIBLE
            btnStart.isEnabled = true

            // Stop the stopwatch when resetting
            stopWatch.stop()
            isPaused = false
        }

        btnPause.setOnClickListener {
            if (isPaused) {
                // Resume
                stopWatch.base = SystemClock.elapsedRealtime() - pauseAt
                stopWatch.start()
                btnPause.text = "Pause"
                isPaused = false
            } else {
                // Pause
                pauseAt = SystemClock.elapsedRealtime() - stopWatch.base
                stopWatch.stop()
                btnPause.text = "Resume"
                isPaused = true
                }
        }
    }
}


