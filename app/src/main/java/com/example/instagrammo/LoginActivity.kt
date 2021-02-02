package com.example.instagrammo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import network.ApiClient
import request.AuthRequest
import response.AuthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener {
            login()
        }

    }

    fun login() {
        val authRequest = AuthRequest(
                username = username.text.toString(),
                password = password.text.toString()
        )
        ApiClient.getClient.doAuth(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                println("hai fallito-----------> " + t.message)
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            }

        })
    }
}