package com.example.fitnessapp.pars.traningRecommendation


import com.google.gson.annotations.SerializedName

data class LosingWeight(
    val description: String,
    val name: String,
    val kkal: String,
    val workout: List<Workout>
)