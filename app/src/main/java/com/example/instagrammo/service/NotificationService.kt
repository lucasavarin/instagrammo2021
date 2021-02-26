package com.example.instagrammo.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.instagrammo.R
import com.example.instagrammo.utils.Constant.CHANNEL_ID
import com.example.instagrammo.utils.Constant.GROUP_APP_NOTIFICATION
import com.example.instagrammo.view.views.home.HomeFragment

class NotificationService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        displayNotification()
        return START_NOT_STICKY
    }

    private fun displayNotification() {
        val notidicationId = 1
        val notificationIntent = Intent(this, HomeFragment.newInstance::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID )
            .setContentTitle("demoTitle")
            .setContentText("This is a demo NOTIFICATION")
            .setSmallIcon(R.mipmap.logo0)
            .setContentIntent(pendingIntent)
            .setGroup(GROUP_APP_NOTIFICATION)
            .build()
        startForeground(notidicationId, notification)

        //TODO Create a Thread
        //stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        val newInstance : NotificationService = NotificationService()
    }

}