package com.lynxspa.instagrammo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import singleton.ApiClient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton.setOnClickListener {
            accendi()
        }


    }

    fun accendi() {
        val authRequest = AuthRequest(
            username = userNameEditText.text.toString(),
            password = passwordEditText.text.toString()
        )
        ApiClient.getClient.doAuth(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                println("hai fallito-----------> " + t.message)
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                println("success response-----------> " + response.body().toString())
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }

}