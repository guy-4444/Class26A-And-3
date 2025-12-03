package com.guy.class26a_and_3

import android.R
import android.media.MediaActionSound
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.guy.class26a_and_3.databinding.ActivityTimerBinding


class Timer1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding

    val DELAY: Long = 1000
    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnStart.setOnClickListener {
            startTimer()
        }

        binding.btnStop.setOnClickListener {
            stopTimer()
        }

        initTimer()

    }

    fun secondlyTask() {
        counter++
        binding.lblInfo.text = "$counter"
        playBeepSound()
    }

    private fun playBeepSound() {
        val sound = MediaActionSound()
        sound.play(MediaActionSound.START_VIDEO_RECORDING)
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    override fun onStop() {
        super.onStop()
        stopTimer()
    }



// -- // -- // -- // -- // -- // -- // -- // -- // -- // -- // -- // -- //

    val handler: Handler = Handler(Looper.getMainLooper())
    var runnable = object : Runnable {
        override fun run() {
            secondlyTask()
            handler.postDelayed(this, DELAY)
        }
    }

    private fun initTimer() {

    }

    fun startTimer() {
        handler.postDelayed(runnable, DELAY)
    }

    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }


}