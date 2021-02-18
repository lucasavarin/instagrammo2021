package com.lynx.instagrammo.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.view.ApplicationContext.Companion.prefs
import com.lynx.instagrammo.networking.AuthRequest
import com.lynx.instagrammo.networking.AuthResponse
import com.lynx.instagrammo.view.MainActivity
import com.lynx.instagrammo.R
import com.lynx.instagrammo.networking.API.ApiClient.authToken
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cbRemeberMe.isChecked = prefs!!.rememberUser
        editTextUsername.setText(prefs?.username)


        btnLogin.setOnClickListener {
            doLogin()
        }

    }

    fun doLogin() {
        val authRequest = AuthRequest(
                username = editTextUsername.text.toString(),
                password = editTextPassword.text.toString()
        )

                     /*-------------- chiamata post doAuth--------------*/
        ApiClient.GetClient.doAuth(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (!response.body()!!.authToken.isNullOrBlank() || !response.body()!!.profileId.isNullOrBlank()) {
                    prefs!!.rememberUser = cbRemeberMe.isChecked
                    prefs!!.username = if(cbRemeberMe.isChecked) editTextUsername.text.toString() else ""
                    prefs!!.userId = response.body()!!.profileId.toString()
                    authToken = response.body()!!.authToken.toString()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this@LoginActivity, "Username/Password errata" , Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {


            }
        })
    }


}
