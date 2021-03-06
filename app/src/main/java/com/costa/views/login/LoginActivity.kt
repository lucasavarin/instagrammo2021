package com.costa.views.login

import android.app.Dialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.costa.instagrammo.R
import com.costa.views.main.MainActivity
import com.costa.instagrammo.R.layout.activity_login
import com.costa.servizi.ApiClient
import com.costa.servizi.ApiClient.authToken
import com.costa.servizi.ApiClient.userId
import com.costa.servizi.AuthRequest
import com.costa.servizi.AuthResponse
import com.costa.utils.prefs
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

   // private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
        et_username.setText(prefs.username)
        cb_restaLoggato.isChecked = prefs.rememberUser
       // progressBar = findViewById(R.id.progressBar)

        bt_login.setOnClickListener {

            login()

        }

    }

    fun login() {
        val reuqest = AuthRequest(

            et_username.text.toString(),

            et_password.text.toString()
        )


        ApiClient.getClient.doAuth(reuqest).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    "i server non sono al momento disponibili",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {


                if (!response.body()!!.authToken.isNullOrBlank() || !response.body()!!.profileId.isNullOrBlank()) {

                    authToken = response.body()!!.authToken.toString()
                    userId = response.body()!!.profileId.toString()
                  //  progressBar.visibility = View.VISIBLE

                    prefs.rememberUser = cb_restaLoggato.isChecked
                    prefs.username = if (prefs.rememberUser) et_username.text.toString() else ""

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "username o password errati",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }
}