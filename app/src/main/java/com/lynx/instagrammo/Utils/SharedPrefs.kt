package com.lynx.instagrammo.Utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs (context: Context){
    private val PREF_FILENAME = "com.lynx.instagrammo.com.lynx.instagrammo.getPrefs"
    private val REMEMBER_USER = "remember_user"
    private val prefs:SharedPreferences = context.getSharedPreferences(PREF_FILENAME, 0)

    var rememberUser:Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()
}