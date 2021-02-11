package singleton

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

val prefs: Prefs by lazy {
    Prefs.prefs!!

}
class Prefs(applicationContext: Context) : Application() {
    companion object{
        var prefs : Prefs?= null
    }

    override fun onCreate() {
        prefs =
            Prefs(applicationContext)
        super.onCreate()

        class Prefs(context: Context){
            private val PREFS_FILENAME = "singleton.Prefs"
            private val REMEMBER_USER = "remember_user"
            private val prefs : SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

            var rememberUser : Boolean
            get() = prefs.getBoolean(REMEMBER_USER, false)
            set(value) = prefs.edit().putBoolean(REMEMBER_USER, value).apply()

        }
    }
}
