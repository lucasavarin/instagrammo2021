package com.lynxspa.instagrammo

import android.app.Application
import com.lynxspa.instagrammo.singleton.Prefs

class InstagrammoApplication : Application() {
    companion object {
        var prefs: Prefs? = null

    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}