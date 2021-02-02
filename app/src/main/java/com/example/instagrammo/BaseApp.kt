package com.example.instagrammo

import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.example.instagrammo.view.login.LogInActivity

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