package com.example.instagrammo.views.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import com.example.instagrammo.R
import com.example.instagrammo.prefs
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.beans.auth.AuthRequest
import com.example.instagrammo.beans.auth.AuthResponse
import com.example.instagrammo.utils.ElementViewConverter.toEditable
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

        if(prefs.rememberMe) {
            username_view.text = prefs.rememberUsername.toEditable()
            rememberme_switch.isChecked = true
        }

        btnAccess.setOnClickListener {

            val username: String = username_view.text.toString()
            val password: String = password_view.text.toString()

            if(rememberme_switch.isChecked) {
                prefs.rememberMe = true
                prefs.rememberUsername = username
            } else {
                prefs.rememberMe = false
                prefs.rememberUsername = ""
            }

            val request = AuthRequest("jsamson", "mson")
            //val request = AuthRequest(username, password)

            ApiClient.GetClient.doAuth(request).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    print("Login Fallito")
                }

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.body()?.result == true) {
                        prefs.rememberToken = response.body()?.authToken.toString()
                        prefs.rememberIdProfile = response.body()?.profileId.toString()
                        val intentLogin = Intent(applicationContext, BaseHomeActivity::class.java)
                        startActivity(intentLogin)
                        finish()
                    } else {
                        Toast.makeText(this@LogInActivity, R.string.loginFallito, Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }

    }
}