package utils.extensions

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME = "utils"
    private val REMEMBER_USER = "remember_user"
    private val REMEMBER_PASSWORD = "remember_token"
    private val PROFILEID = "profile_id"
    private val PROFILETOKEN = "profile_token"
    private val USERNAME = "username"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

    var rememberPassword: String?
        get() = prefs.getString(REMEMBER_PASSWORD, null)
        set(value) = prefs.edit().putString(REMEMBER_USER, value).apply()

    var profileId: String
        get() = prefs.getString(PROFILEID, "")!!
        set(value) = prefs.edit().putString(PROFILEID, value).apply()

    var authToken: String
        get() = prefs.getString(PROFILETOKEN, "")!!
        set(value) = prefs.edit().putString(PROFILETOKEN, value).apply()

    var username: String
        get() = prefs.getString(USERNAME, "")!!
        set(value) = prefs.edit().putString(USERNAME, value).apply()
}