package com.example.fitnessapp.pars.traningRecommendation


data class LosingWeight(
    val description: String,
    val name: String,
    val kkal: String,
    val workout: List<TrainingContent>
)