package utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME = "utils"
    private val REMEMBER_USER = "remember_user"
    private val REMEMBER_PASSWORD = "remember_token"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var rememberUser: Boolean
        get() = prefs.getBoolean(REMEMBER_USER, false)
        set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

    var rememberPassword: String?
        get() = prefs.getString(REMEMBER_PASSWORD, null)
        set(value) = prefs.edit().putString(REMEMBER_USER, value).apply()
}