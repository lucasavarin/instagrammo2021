package utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME = "utils"
    private val REMEMBER_USER = "remember_user"
    private val REMEMBER_PASSWORD = "remember_token"
    private val FOLLOWERID = "follower_id"
    private val FOLLOWERTOKEN = "follower_token"
    private val USERNAME = "username"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

    var rememberPassword: String?
        get() = prefs.getString(REMEMBER_PASSWORD, null)
        set(value) = prefs.edit().putString(REMEMBER_USER, value).apply()

    var followerId: String
        get() = prefs.getString(FOLLOWERID, "")!!
        set(value) = prefs.edit().putString(FOLLOWERID, value).apply()

    var followerToken: String
        get() = prefs.getString(FOLLOWERTOKEN, "")!!
        set(value) = prefs.edit().putString(FOLLOWERTOKEN, value).apply()

    var username: String
        get() = prefs.getString(USERNAME, "")!!
        set(value) = prefs.edit().putString(USERNAME, value).apply()
}