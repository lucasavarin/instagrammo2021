package com.costa.utils

import android.app.Application

class ApplicationContext : Application(){
    companion object{
        var prefs: Prefs?=null
    }

    override fun onCreate() {
        prefs=Prefs(applicationContext)
        super.onCreate()
    }
}