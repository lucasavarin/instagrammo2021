package com.example.instagrammo.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class NotificationService : Service() {

    override fun onCreate() {
        super.onCreate()
    }
/*
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       // val notification = NotificationCompat.Builder(this, )

    }
*/
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}