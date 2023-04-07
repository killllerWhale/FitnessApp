package com.example.fitnessapp.pars.traningRecommendation


import com.google.gson.annotations.SerializedName

data class TrainingRecommendationsList(
    @SerializedName("plan")
    val plan: List<TrainingFormsList>
)