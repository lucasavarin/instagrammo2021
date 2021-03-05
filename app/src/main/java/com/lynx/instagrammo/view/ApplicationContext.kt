package com.lynx.instagrammo.view

import android.app.Application
import com.lynx.instagrammo.db.DbHelper
import com.lynx.instagrammo.persistence.SharedPrefs

class ApplicationContext: Application(){
        companion object{
            var prefs: SharedPrefs? = null
            var dbHelper: DbHelper? = null
        }

        override fun onCreate() {
            prefs = SharedPrefs(applicationContext)
            dbHelper = DbHelper(applicationContext)
            super.onCreate()
        }

}