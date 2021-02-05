package com.costa.instagrammo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.costa.instagrammo.R.layout.activity_main
import com.costa.servizi.ApiClient
import com.costa.servizi.AuthRequest
import com.costa.servizi.AuthResponse
import com.costa.utils.prefs
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var authToken:String? = null
    var userId:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
          et_username.setText(prefs.username)
          cb_restaLoggato.isChecked= prefs.rememberUser

        bt_login.setOnClickListener {

            login()

        }

    }

    fun login() {
        val reuqest = AuthRequest(

                et_username.text.toString(),

                et_password.text.toString())


        ApiClient.getClient.doAuth(reuqest).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "i server non sono al momento disponibili", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {



                if(!response.body()!!.authToken.isNullOrBlank()||!response.body()!!.profileId.isNullOrBlank()) {

                    authToken= response.body()!!.authToken.toString()
                    userId=response.body()!!.profileId.toString()

                    //TODO: non funziona il prefs
                    prefs!!.rememberUser= cb_restaLoggato.isChecked
                    prefs!!.username=if(prefs.rememberUser) et_username.text.toString() else ""

                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                 startActivity(intent)
                }else
                {
                    Toast.makeText(this@MainActivity, "username o password errati", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}