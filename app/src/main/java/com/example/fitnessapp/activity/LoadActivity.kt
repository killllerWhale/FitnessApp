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
import com.example.fitnessapp.profile.MyBroadcastReceiverPrefs
import java.util.*


lateinit var prefs: SharedPreferences

class LoadActivity : AppCompatActivity() {

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        prefs = getSharedPreferences("themes", Context.MODE_PRIVATE)
        val intente = if (prefs.getInt("user", 0) == 1) {
            Intent(this, MainActivity::class.java)
        } else {
            loadService()
            Intent(this, EntryActivity::class.java)
        }
        startActivity(intente)
    }

    private fun loadService() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Создаем PendingIntent для запуска BroadcastReceiver
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        // Устанавливаем время, когда нужно запустить BroadcastReceiver
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 0)
        }

        // Устанавливаем повторение каждый день
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        val alarmManagerPrefs = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val broadcastIntent = Intent(this, MyBroadcastReceiverPrefs::class.java)
        val pendingIntentPrefs = PendingIntent.getBroadcast(this, 0, broadcastIntent, 0)

        val calendarPrefs = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
        }

        alarmManagerPrefs.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendarPrefs.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntentPrefs
        )
    }
}