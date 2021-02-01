package com.lynx.instagrammo.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lynx.instagrammo.R

class HomeActivity : AppCompatActivity(){

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
    }
}