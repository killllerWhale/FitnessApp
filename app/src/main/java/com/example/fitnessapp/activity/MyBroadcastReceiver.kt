package com.example.fitnessapp.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.fitnessapp.R

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Создаем уведомление
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "channelId"
        val channel =
            NotificationChannel(channelId, "Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(context.getString(R.string.fitbalance))
            .setContentText(context.getString(R.string.Text4))
            .setSmallIcon(R.drawable.icon_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        // Отображаем уведомление
        notificationManager.notify(0, notification)
    }
}