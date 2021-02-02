package com.lynx.instagrammo.Login
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lynx.instagrammo.API.ApiClient
import com.lynx.instagrammo.AuthRequest
import com.lynx.instagrammo.AuthResponse
import com.lynx.instagrammo.Home.HomeActivity
import com.lynx.instagrammo.R
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

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
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })

    }


}