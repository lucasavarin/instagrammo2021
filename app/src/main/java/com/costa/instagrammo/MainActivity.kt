package com.costa.instagrammo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.costa.instagrammo.R.layout.activity_main
import com.costa.servizi.ApiClient
import com.costa.servizi.AuthRequest
import com.costa.servizi.AuthResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        bt_login.setOnClickListener {

         login()

        }

    }

fun login()
{
    val reuqest=AuthRequest(

        et_username.text.toString(),

        et_password.text.toString())

    ApiClient.getClient.doAuth(reuqest).enqueue(object : Callback<AuthResponse> {
        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
           println("errore")
        }

        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
           /*     val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)*/
        }


    })

}
}