package com.lynx.instagrammo.view

import android.app.Application
import com.lynx.instagrammo.persistence.SharedPrefs

class ApplicationContext: Application(){
        companion object{
            var prefs: SharedPrefs? = null
        }

        override fun onCreate() {
            prefs = SharedPrefs(applicationContext)
            super.onCreate()
        }
}