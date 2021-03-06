package com.example.instagrammo.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

import com.example.instagrammo.MainActivity
import com.example.instagrammo.R
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.response.PostNumberResponse
import com.example.instagrammo.view.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForegroundService : Service() {
    private val CHANNEL_ID = "ForegroundService MIJO"
    private var diff: Int = 0
    private var post: Int = 0
    private var SECOND: Long = 5000

    companion object {
        fun startService(context: Context, message: String) {
            val startIntent = Intent(context, ForegroundService::class.java)
            startIntent.putExtra("inputExtra", message)
            ContextCompat.startForegroundService(context, startIntent)
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, ForegroundService::class.java)
            context.stopService(stopIntent)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        createRunnable(pendingIntent)
        return START_NOT_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    private fun createRunnable(pending: PendingIntent) {
        Handler().postDelayed(object : Runnable {
            override fun run() {
                ApiClient.getClient.getListPostNumber()
                    .enqueue(object : Callback<PostNumberResponse> {
                        override fun onFailure(call: Call<PostNumberResponse>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<PostNumberResponse>,
                            response: Response<PostNumberResponse>
                        ) {

                            if (post != 0) {
                                diff = response.body()!!.payload.toInt() - post
                            }
                            post = response.body()!!.payload.toInt()

                            if (diff != 0) {
                                prefs.postNumber = prefs.postNumber + diff
                                prefs.diffPostNumber = true
                            }


                            if (!prefs.diffPostNumber) {
                                prefs.postNumber = 0
                            }

                            val notification =
                                NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                                    .setContentTitle("Recupero Post")
                                    .setContentText("Nuovi post pubblicati : ${prefs.postNumber}")
                                    .setSmallIcon(R.drawable.instagrammo)
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setContentIntent(pending)
                                    .build()

                            startForeground(1, notification)
                        }
                    })
                Handler().postDelayed(this, SECOND)
            }
        }, SECOND)
    }
}