package com.example.fitnessapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityEntryBinding
import com.example.fitnessapp.start.EntryFragment

class EntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(EntryFragment())


    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}