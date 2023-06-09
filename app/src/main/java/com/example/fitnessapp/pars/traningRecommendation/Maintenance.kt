package com.example.fitnessapp.pars.traningRecommendation


import com.google.gson.annotations.SerializedName

data class Maintenance(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("kkal")
    val kkal: String,
    @SerializedName("workout")
    val workout: List<TrainingContent>
)