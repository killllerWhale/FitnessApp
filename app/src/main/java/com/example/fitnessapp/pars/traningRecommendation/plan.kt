package com.example.fitnessapp.pars.traningRecommendation


import com.google.gson.annotations.SerializedName

data class plan(
    @SerializedName("plan")
    val plan: List<PlanX>
)