package com.lynxspa.instagrammo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener{
            accendi()
        }



    }

    fun accendi() {
        val authRequest = AuthRequest(
            username = userNameEditText.text.toString(),
            password = passwordEditText.text.toString()

        )
    }
}