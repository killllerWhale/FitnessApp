package com.example.fitnessapp.pars.traningRecommendation


import com.google.gson.annotations.SerializedName

data class Workout(
    @SerializedName("coll")
    val coll: Int,
    @SerializedName("collB")
    val collB: Int,
    @SerializedName("name")
    val name: String
)