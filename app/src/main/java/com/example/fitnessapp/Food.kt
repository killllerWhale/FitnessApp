package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.ActivityFoodBinding

class Food : AppCompatActivity() {

    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
                else -> {
                    loadFragment(ProfileFragment())
                    true
                }
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}