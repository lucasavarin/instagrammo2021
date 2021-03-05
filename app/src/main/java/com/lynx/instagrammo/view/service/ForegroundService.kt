package com.lynx.instagrammo.view.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.lynx.instagrammo.R
import com.lynx.instagrammo.view.login.LoginActivity

class ForegroundService : Service() {

    val CHANNEL_ID: String = "MyServiceChannel"

    companion object {
        fun startService(context: Context, message: String) {
            val startServiceIntent = Intent(context, ForegroundService::class.java)
            startServiceIntent.putExtra("inputExtra", message)
            ContextCompat.startForegroundService(context, startServiceIntent)
        }

        fun stopService(context: Context) {
            val stopServiceIntent = Intent(context, ForegroundService::class.java)
            context.stopService(stopServiceIntent)
        }
    }

    //onCreate
    override fun onCreate() {
        super.onCreate()
        crateNotificationChannel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    //onStartCommand
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val input = intent!!.getStringExtra("inputExtra")
        crateNotificationChannel()
        val notificationIntent = Intent(this, LoginActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Recupero Post")
                .setContentText("Nuovi post pubblicati $input")
                .setSmallIcon(R.drawable.ic_profile)
                .setContentIntent(pendingIntent)
                .build()

        startForeground(1, notification)

        return START_NOT_STICKY
    }

    private fun crateNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(CHANNEL_ID, "My Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }

    }


}