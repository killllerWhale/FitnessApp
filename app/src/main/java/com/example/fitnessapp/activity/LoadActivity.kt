package com.example.fitnessapp.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.fitnessapp.R
import java.util.*

lateinit var prefs: SharedPreferences

class LoadActivity : AppCompatActivity() {

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        val context = this
        val notificationManager = NotificationManagerCompat.from(context)
        val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.drawable.icon_notification)
            .setContentTitle("Заголовок уведомления")
            .setContentText("Текст уведомления")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Показ уведомления
        notificationManager.notify(1, builder.build())


        prefs = getSharedPreferences("themes", Context.MODE_PRIVATE)
        val intente = if (prefs.getInt("user", 0) == 1) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, EntryActivity::class.java)
        }
        startActivity(intente)
    }
}