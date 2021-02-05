package com.example.instagrammo.view

import android.app.Application
import com.example.instagrammo.Prefs

class ApplicationContext : Application() {
    companion object{
        var prefs: Prefs? = null

    }

    override fun onCreate() {
        prefs= Prefs(applicationContext)
        super.onCreate()
    }
}