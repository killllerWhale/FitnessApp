package com.example.fitnessapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.ActivityEntryBinding
import com.example.fitnessapp.start.EntryFragment

class EntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntryBinding
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("themes", Context.MODE_PRIVATE)
        if (prefs.getInt("user", 0) == 1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }else{
            loadFragment(EntryFragment())
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}