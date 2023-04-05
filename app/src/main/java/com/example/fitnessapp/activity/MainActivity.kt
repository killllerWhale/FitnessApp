package com.example.fitnessapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fitnessapp.profile.ProfileFragment
import com.example.fitnessapp.profile.ProgressFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.example.fitnessapp.food.FoodFragment
import com.example.fitnessapp.workout.TrainingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(ProfileFragment())
        binding.bottomNavigation.selectedItemId = R.id.profile
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.training -> {
                    loadFragment(TrainingFragment())
                    true
                }
                R.id.food -> {
                    loadFragment(FoodFragment())
                    true
                }
                R.id.progress -> {
                    loadFragment(ProgressFragment())
                    true
                }
                else -> {
                    loadFragment(ProfileFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

}
