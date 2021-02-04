package com.costa.utils

import android.app.Application
import android.util.Log

class ApplicationContext : Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}