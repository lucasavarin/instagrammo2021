package com.costa.instagrammo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.costa.views.HomeFragment
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashTime = 2000
        val homeIntent = Intent(this@SplashActivity, LoginActivity::class.java)

        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        }, splashTime.toLong())
    }
}