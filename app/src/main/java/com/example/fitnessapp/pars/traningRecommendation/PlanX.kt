package com.example.fitnessapp.pars.traningRecommendation


import com.google.gson.annotations.SerializedName

data class PlanX(
    @SerializedName("losingWeight")
    val losingWeight: List<LosingWeight>,
    @SerializedName("maintenance")
    val maintenance: List<Maintenance>,
    @SerializedName("weightGain")
    val weightGain: List<WeightGain>
)