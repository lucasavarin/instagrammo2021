package com.example.instagrammo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagrammo.MainActivity
import com.example.instagrammo.R
import com.example.instagrammo.view.prefs
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.request.AuthRequest
import com.example.instagrammo.response.AuthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        checkbox_Remember.isChecked = prefs!!.rememberUser
        username.setText(prefs!!.username)

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
                prefs!!.rememberUser = checkbox_Remember.isChecked
                prefs!!.username = if (prefs!!.rememberUser) username.text.toString() else ""
                prefs!!.idProfilo = response.body()?.profileId.toString()
                prefs!!.token = response.body()?.authToken.toString()

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

        })
    }
}