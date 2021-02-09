package com.lynx.instagrammo.Login
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lynx.instagrammo.API.ApiClient
import com.lynx.instagrammo.ApplicationContext.Companion.prefs
import com.lynx.instagrammo.AuthRequest
import com.lynx.instagrammo.AuthResponse
import com.lynx.instagrammo.Home.HomeActivity
import com.lynx.instagrammo.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(cbRemeberMe.isChecked ){
        editTextUsername.setText(prefs?.username)
        Log.i("INFORMATION :" , prefs?.username)}

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
                prefs!!.rememberUser = cbRemeberMe.isChecked
                prefs!!.username = editTextUsername.text.toString()

                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })

    }


}