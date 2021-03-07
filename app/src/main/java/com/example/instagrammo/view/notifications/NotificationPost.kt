package com.example.instagrammo.view.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.notification.NotificationArguments
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.utils.Constant
import com.example.instagrammo.utils.Constant.CONST_GROUP_APP_NOTIFICATION
import com.example.instagrammo.utils.Constant.CONST_NOTIFICATION_DATA
import com.example.instagrammo.view.BaseHomeActivity
import com.squareup.picasso.Picasso

class NotificationPost(
    val intent : Intent,
    val context: Context
){

    fun buildNotificationPost(title: String, description : String, iconProfile: Bitmap) : Notification {
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                context,
                0,
                intent,
                0
            )

        return NotificationCompat.Builder(context, Constant.CONST_CHANNEL_ID_1)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.mipmap.logo0)
            .setLargeIcon(iconProfile)
            .setColor(context.getColor(R.color.celadonBlue))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_SOCIAL)
            .setGroup(CONST_GROUP_APP_NOTIFICATION)
            .setContentIntent(pendingIntent)
            .build()
    }

    companion object {
        var newInstance : Notification = Notification()
    }
}