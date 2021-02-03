package com.example.instagrammo.view.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import com.example.instagrammo.prefs
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.retrofit.AuthRequest
import com.example.instagrammo.retrofit.AuthResponse
import com.example.instagrammo.view.BaseActivity
import com.example.instagrammo.view.home.HomeActivity
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
            val intentLogin = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intentLogin)

            ApiClient.GetClient.doAuth(request).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    print("Login Fallito")
                }

                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if(!response.body()?.authToken.isNullOrEmpty()){
                        prefs.rememberToken = response.body()?.authToken.toString()
                    }
                }

            })
        }

    }
}