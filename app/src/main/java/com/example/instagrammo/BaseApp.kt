package com.example.instagrammo

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.retrofit.AuthRequest
import com.example.instagrammo.retrofit.AuthResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseApp : Application() {
    companion object {
        var prefs : Prefs? = null
    }

    override fun onCreate(){
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}

//variabile globale che punta alla classe Prefs
val prefs : Prefs by lazy {
    BaseApp.prefs!!
}