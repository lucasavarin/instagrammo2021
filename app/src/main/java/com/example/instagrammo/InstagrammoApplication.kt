package com.example.instagrammo

import android.app.Application
import com.example.instagrammo.utils.SharePrefs

class InstagrammoApplication : Application() {
    companion object {
        var prefs : SharePrefs? = null
    }

    override fun onCreate(){
        prefs = SharePrefs(applicationContext)
        super.onCreate()
    }
}

//variabile globale che punta alla classe Prefs
val prefs : SharePrefs by lazy {
    InstagrammoApplication.prefs!!
}