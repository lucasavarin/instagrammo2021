package com.costa.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME = "com.costa.utils.prefs"
    private val REMEMBER_USER = "REMEMBER_USER"
    private val USERNAME = "USERNAME"
    private val POST_VISUALIZZATI="POST_VISUALIZZATI"
    private val POST_NEWS="POST_NEWS"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

    var username: String
        get() = prefs.getString(USERNAME, "")!!
        set(value) = prefs.edit().putString(USERNAME, value).apply()
    var postVisualizzati: Int
        get() = prefs.getInt(POST_VISUALIZZATI, 0)
        set(value) = prefs.edit().putInt(POST_VISUALIZZATI, value).apply()
    var postNews: Int
        get() = prefs.getInt(POST_NEWS, 0)
        set(value) = prefs.edit().putInt(POST_NEWS, value).apply()
}