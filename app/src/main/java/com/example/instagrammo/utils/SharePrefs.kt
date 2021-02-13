package com.example.instagrammo.utils

import android.content.Context
import android.content.SharedPreferences

class SharePrefs (context: Context){
    private val PREFS_FILENAME = "com.example.instagrammo"
    private val REMEMBER_ME = "remember_me"
    private val REMEMBER_USERNAME = "remember_username"
    private val REMEMBER_TOKEN = "remember_token"
    private val REMEMBER_ID_PROFILE = "remember_id_profile"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var rememberMe: Boolean
        get() = prefs.getBoolean(REMEMBER_ME, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_ME, value).apply()

    var rememberUsername: String
        get() = prefs.getString(REMEMBER_USERNAME, "")!!
        set(value) = prefs.edit().putString(REMEMBER_USERNAME, value).apply()

    var rememberToken: String
        get() = prefs.getString(REMEMBER_TOKEN, "")!!
        set(value) = prefs.edit().putString(REMEMBER_TOKEN, value).apply()

    var rememberIdProfile: String
        get() = prefs.getString(REMEMBER_ID_PROFILE, "")!!
        set(value) = prefs.edit().putString(REMEMBER_ID_PROFILE, value).apply()
}