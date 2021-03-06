package com.example.instagrammo

import android.content.Context
import android.content.SharedPreferences

class Prefs(val context : Context) {

    private val PREFS_FILNAME = "com.example.instagrammo.prefs"
    private val REMEMBER_USER= "REMEMBER_USER"
    private val USERNAME= "USERNAME"
    private val UTENTE= "idProfilo"
    private val TOKEN = "token"
    private val POST_NUMBER = "POST_NUMBER"
    private val DIFF_NUMB_POST = "DIFF_NUMB_POST"


    private val prefs : SharedPreferences = context.getSharedPreferences(PREFS_FILNAME, 0)


    var rememberUser: Boolean
       get() = prefs.getBoolean(REMEMBER_USER,false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER,value).apply()

    var username : String
        get() = prefs.getString(USERNAME,"")!!
        set(value) = prefs.edit().putString(USERNAME,value).apply()

    var idProfilo :String
        get() = prefs.getString(UTENTE,"")?: ""
        set(value) = prefs.edit().putString(UTENTE,value).apply()

    var token : String
        get() = prefs.getString(TOKEN, "")?: ""
        set(value) = prefs.edit().putString(TOKEN, value).apply()
    var postNumber: Int
        get() = prefs.getInt(POST_NUMBER, 0)
        set(value) = prefs.edit().putInt(POST_NUMBER, value).apply()

    var diffPostNumber : Boolean
        get() = prefs.getBoolean(DIFF_NUMB_POST, false)
        set(value) = prefs.edit().putBoolean(DIFF_NUMB_POST, value).apply()
}