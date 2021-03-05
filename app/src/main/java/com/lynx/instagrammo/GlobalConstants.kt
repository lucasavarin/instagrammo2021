package com.lynx.instagrammo

import com.lynx.instagrammo.db.DbHelper
import com.lynx.instagrammo.persistence.SharedPrefs
import com.lynx.instagrammo.view.ApplicationContext

val prefs: SharedPrefs by lazy {
    ApplicationContext.prefs!!
}

val dbHelper: DbHelper by lazy {
    ApplicationContext.dbHelper!!
}

