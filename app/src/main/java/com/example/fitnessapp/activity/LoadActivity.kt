package com.example.fitnessapp.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitnessapp.R

lateinit var prefs: SharedPreferences
class LoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        prefs = getSharedPreferences("themes", Context.MODE_PRIVATE)
        if (prefs.getInt("user", 0) == 1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, EntryActivity::class.java)
            startActivity(intent)
        }
    }
}