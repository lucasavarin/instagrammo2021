package com.costa.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME = "com.costa.utils.prefs"
    private val REMEMBER_USER = "REMEMBER_USER"
    private val USERNAME = "USERNAME"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

    var username: String
        get() = prefs.getString(USERNAME, "")!!
        set(value) = prefs.edit().putString(USERNAME, value).apply()

}