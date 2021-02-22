package utils.extensions

import android.app.Application

class BaseApp: Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}

val prefs: Prefs by lazy {
    BaseApp.prefs!!
}