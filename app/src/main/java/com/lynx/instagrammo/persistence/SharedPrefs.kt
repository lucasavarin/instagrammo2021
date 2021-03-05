package com.lynx.instagrammo.persistence

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {
    private val PREF_FILENAME = "com.lynx.instagrammo.prefs"
    private val REMEMBER_USER = "remember_user"
    private val USERNAME = "username"
    private val AUTHTOKEN = "authToken"
    private val USER_ID = "userId"
    private val NUMBER_POSTS = "numberPosts"
    private val POSTS_DIFFERENCE = "postsDifference"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_FILENAME, 0)

    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

    var username: String
        get() = prefs.getString(USERNAME, "")!!
        set(value) = prefs.edit().putString(USERNAME, value).apply()

    var authToken: String
        get() = prefs.getString(AUTHTOKEN, "")!!
        set(value) = prefs.edit().putString(AUTHTOKEN, value).apply()

    var userId: String
        get() = prefs.getString(USER_ID, "")!!
        set(value) = prefs.edit().putString(USER_ID, value).apply()

    var oldPosts: Int
        get() = prefs.getInt(NUMBER_POSTS, 0)!!
        set(value) = prefs.edit().putInt(NUMBER_POSTS, value).apply()

    var postsDifference: Int
        get() = prefs.getInt(POSTS_DIFFERENCE, 0)
        set(value) = prefs.edit().putInt(POSTS_DIFFERENCE, value).apply()

}