package com.example.instagrammo.view.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.instagrammo.view.views.follow.FollowFragment
import com.example.instagrammo.view.views.home.HomeFragment
import com.example.instagrammo.views.BaseActivity

class Notification/* : BaseActivity(){

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "com.qwerty.instagrammo.view.notifications"
    private val description = "attiva notifiche"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(channelId, "DemoChannel", description)
    }

    private fun displayNotification() {
        val notidicationId = 45
        val tapResultIntent = Intent(this, HomeFragment.newInstance::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("demoTitle")
            .setContentText("This is a demo NOTIFICATION")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(notidicationId, notification)


    }

    private fun createNotificationChannel(id: String, name: String, channelDescption: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                id,
                name,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                //description = channelDescption
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        var newInstance : Notification = Notification()
    }
}*/