package com.example.instagrammo

import android.content.Context
import android.content.SharedPreferences

class Prefs(val context : Context) {

    private val PREFS_FILNAME = "com.example.instagrammo.prefs"
    private val REMEMBER_USER= "REMEMBER_USER"
    private val USERNAME= "USERNAME"


    private val prefs : SharedPreferences = context.getSharedPreferences(PREFS_FILNAME, 0)


    var rememberUser: Boolean
       get() = prefs.getBoolean(REMEMBER_USER,false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER,value).apply()

    var username : String
        get() = prefs.getString(USERNAME,"")!!
        set(value) = prefs.edit().putString(USERNAME,value).apply()
}