package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fitnessapp.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {

    lateinit var binding: ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enter.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.go.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}