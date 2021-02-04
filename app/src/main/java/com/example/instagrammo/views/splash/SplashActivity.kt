package com.example.instagrammo.views.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import com.example.instagrammo.views.login.LogInActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentLogin = Intent(applicationContext, LogInActivity::class.java)
        val bundle = Bundle()
        bundle.putString("prova", "valore")
        startActivity(intentLogin, bundle)
    }
}