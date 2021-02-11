package com.lynx.instagrammo

import android.app.Application
import com.lynx.instagrammo.utils.SharedPrefs

class ApplicationContext: Application(){
        companion object{
            var prefs: SharedPrefs? = null
        }

        override fun onCreate() {
            prefs = SharedPrefs(applicationContext)
            super.onCreate()
        }
}