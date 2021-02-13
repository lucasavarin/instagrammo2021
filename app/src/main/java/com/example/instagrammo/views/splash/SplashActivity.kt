package com.example.instagrammo.views.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import com.example.instagrammo.views.login.LogInActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashActivity, LogInActivity::class.java))
            finish()
        }, 3000)
    }
}