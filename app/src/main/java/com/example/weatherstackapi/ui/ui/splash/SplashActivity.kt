package com.example.weatherstackapi.ui.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherstackapi.R
import com.example.weatherstackapi.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.elevation= 0F
        supportActionBar?.elevation = 0F;
        setContentView(R.layout.activity_splash)

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 500)
    }
}