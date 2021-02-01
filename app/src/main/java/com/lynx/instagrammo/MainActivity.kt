package com.lynx.instagrammo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lynx.instagrammo.API.ApiClient
import com.lynx.instagrammo.HomePage.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            doLogin()
        }

    }

    fun doLogin() {
        val authRequest = AuthRequest(
            username = editTextUsername.text.toString(),
            password = editTextPassword.text.toString()
        )
        ApiClient.getClient.doAuth(authRequest).enqueue(object : Callback<AuthResponse>
        {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                println("Error : ${t.message}" )
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })

    }


}