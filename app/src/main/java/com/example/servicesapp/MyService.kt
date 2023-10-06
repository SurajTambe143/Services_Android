package com.example.servicesapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MyService : Service() {
    lateinit var mp:MediaPlayer
    val CHANNEL_ID="1"
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val uri:Uri?=Uri.parse("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/ed/78/93/ed78938d-266e-8527-d698-02d4eec692ef/mzaf_4493853989989508188.plus.aac.ep.m4a")
        Log.e("S", "onStartCommand:${uri} ")

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description=descriptionText

            val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            val nt = Notification.Builder(this, CHANNEL_ID)
                .setContentText("Hiiii")
                .build()
            startForeground(122,nt)
        }else{
            val nt = Notification.Builder(this,CHANNEL_ID)
                .setContentText("Hiiii")
                .build()
            startForeground(122,nt)
        }

        mp = MediaPlayer.create(this,uri)
        mp.isLooping=true
        mp.start()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.e("S", "onDestroy:")
        mp.stop()
    }
}