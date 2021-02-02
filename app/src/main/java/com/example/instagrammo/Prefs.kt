package com.example.instagrammo

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context){
    private val PREFS_FILENAME = "com.example.instagrammo"
    private val REMEMBER_USER = "remember_user"
    private val REMEMBER_TOKEN = "remember_token"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

    var rememberToken: String?
        get() = prefs.getString(REMEMBER_TOKEN, null)
        set(value) = prefs.edit().putString(REMEMBER_USER, value).apply()
}