package com.example.instagrammo

import android.app.Application
import com.example.instagrammo.utils.SharePrefs
import com.example.instagrammo.view.notifications.Notification

//variabile globale che punta alla classe Prefs
val prefs : SharePrefs by lazy {
    InstagrammoApplication.prefs!!
}

class InstagrammoApplication : Application() {
    companion object {
        var prefs : SharePrefs? = null
        var profilePrefs : SharePrefs? = null
    }

    override fun onCreate(){
        prefs = SharePrefs(applicationContext)
        //Notification
        super.onCreate()
    }

}

