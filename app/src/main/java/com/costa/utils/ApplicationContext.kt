package com.costa.utils

import android.app.Application
import com.costa.dataBaseSQLLite.DbHelper

class ApplicationContext : Application() {
    companion object {
        var prefs: Prefs? = null
        var db: DbHelper?=null
    }

    override fun onCreate() {
        super.onCreate()
        db=DbHelper(applicationContext)
        prefs = Prefs(applicationContext)
    }
}