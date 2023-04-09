package com.example.fitnessapp.profile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiverPrefs : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Обновляем значения SharedPreferences
        val preferences = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        preferences?.edit()?.putString("caloriesBurned", "0")?.apply()
        preferences?.edit()?.putString("water", "0;0")?.apply()
        preferences?.edit()?.putString("food_info", "0;0;0;0")?.apply()
    }
}