package com.example.instagrammo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.instagrammo.utils.Constant.CHANNEL_ID
import com.example.instagrammo.utils.Constant.CHANNEL_NAME
import com.example.instagrammo.utils.SharePrefs

//variabile globale che punta alla classe Prefs
val prefs : SharePrefs by lazy {
    InstagrammoApplication.prefs!!
}

class InstagrammoApplication : Application() {

    companion object {
        var prefs : SharePrefs? = null
    }

    override fun onCreate(){
        prefs = SharePrefs(applicationContext)
        createNotificationChannel()
        super.onCreate()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}

