package com.example.fitnessapp.activity

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.R
import java.util.*


lateinit var prefs: SharedPreferences

class LoadActivity : AppCompatActivity() {

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)


        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Создаем PendingIntent для запуска BroadcastReceiver
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        // Устанавливаем время, когда нужно запустить BroadcastReceiver
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 8)
        }

        // Устанавливаем повторение каждый день
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        prefs = getSharedPreferences("themes", Context.MODE_PRIVATE)
        val intente = if (prefs.getInt("user", 0) == 1) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, EntryActivity::class.java)
        }
        startActivity(intente)
    }
}