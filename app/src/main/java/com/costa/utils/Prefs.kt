package com.costa.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME = "com.costa.utils.prefs"
    private val REMEMBER_USER = "remember_user"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

}