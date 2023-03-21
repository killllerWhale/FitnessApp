package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitnessapp.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.training -> {
                    startActivity(Intent(this, Training::class.java))
                    true
                }
                R.id.food -> {
                    startActivity(Intent(this, Food::class.java))
                    true
                }
                else -> {startActivity(Intent(this, Profile::class.java))
                    true}
            }
        }


    }
}