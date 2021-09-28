package com.example.mvmm.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.example.mvmm.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    override fun onResume() { // geri gelince countdowntimer çalışması için
        var animation= AnimationUtils.loadAnimation(this,R.anim.slide_up)
        var animationDown=AnimationUtils.loadAnimation(this,R.anim.slide_down)
        imgSun.animation=animation
        imgCloud.animation=animationDown
        object : CountDownTimer(5000,1000){ //animasyon devam ederken süre sayıp main activity geçecek
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                var intent= Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)

            }

        }.start()
        super.onResume()
    }
}