package com.example.instagrammo.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.notification.NotificationArguments
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.utils.Constant.CONST_CHANNEL_ID
import com.example.instagrammo.utils.Constant.CONST_GROUP_APP_NOTIFICATION
import com.example.instagrammo.utils.Constant.CONST_NOTIFICATION_DATA
import com.example.instagrammo.view.views.home.HomeFragment
import com.squareup.picasso.Picasso

class NotificationService : Service(){


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.extras?.getParcelableArrayList<NotificationArguments>(CONST_NOTIFICATION_DATA)
            ?.forEach { data ->
            displayNotification(data.nameProfile, data.description, data.iconProfile)
        }
        return START_NOT_STICKY
    }

    private fun displayNotification(title: String, description : String, iconProfile: String) {
        val notidicationId = 1
        val notificationIntent = Intent(this, HomeFragment.newInstance::class.java)
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        Picasso.get().load(iconProfile).resize(200,200).transform(CircleTransform())

        val notification = NotificationCompat.Builder(this, CONST_CHANNEL_ID )
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.mipmap.logo0)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setGroup(CONST_GROUP_APP_NOTIFICATION)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(notidicationId, notification)
        //TODO Create a Thread
        //stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    companion object {
        val newInstance : NotificationService = NotificationService()
    }

}