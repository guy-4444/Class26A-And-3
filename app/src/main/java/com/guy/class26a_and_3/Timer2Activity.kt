package com.guy.class26a_and_3

import android.media.MediaActionSound
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.guy.class26a_and_3.databinding.ActivityTimerBinding
import java.util.Timer
import java.util.TimerTask


class Timer2Activity : AppCompatActivity() {

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

    var timer = Timer()

    private fun initTimer() {

    }

    fun startTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                secondlyTask()
            }
        }, 0, DELAY)
    }

    fun stopTimer() {
        timer.cancel()
    }


}