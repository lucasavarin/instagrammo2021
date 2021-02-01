package com.example.instagrammo.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.instagrammo.R
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.retrofit.AuthRequest
import com.example.instagrammo.retrofit.AuthResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username : String = username_view.text.toString()
        val password : String = password_view.text.toString()

        val request = AuthRequest(username, password)

        btnAccess.setOnClickListener {
            Toast.makeText(this@LogIn, "Bottone premuto", Toast.LENGTH_LONG).show()
            ApiClient.GetClient.doAuth(request).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(this@LogIn, "LogIn fallito", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    Toast.makeText(this@LogIn, response.message(), Toast.LENGTH_LONG).show()
                }

            })
        }

    }
}