package com.example.fitnessapp.pars.traningRecommendation


import com.google.gson.annotations.SerializedName

data class LosingWeight(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("workout")
    val workout: List<Workout>
)