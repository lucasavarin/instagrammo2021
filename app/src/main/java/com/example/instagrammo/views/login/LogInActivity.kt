package com.example.instagrammo.views.login

import android.content.Intent
import android.os.Bundle
import com.example.instagrammo.R
import com.example.instagrammo.prefs
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.retrofit.AuthRequest
import com.example.instagrammo.retrofit.AuthResponse
import com.example.instagrammo.views.BaseActivity
import com.example.instagrammo.views.BaseHomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = username_view.text
        val password = password_view.text

        val request = AuthRequest("lsavarin", "lucasava")

        btnAccess.setOnClickListener {

            ApiClient.GetClient.doAuth(request).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    print("Login Fallito")
                }

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {

                        prefs.rememberToken = response.body()?.authToken.toString()
                        val intentLogin = Intent(applicationContext, BaseHomeActivity::class.java)
                        startActivity(intentLogin)

                }

            })
        }

    }
}