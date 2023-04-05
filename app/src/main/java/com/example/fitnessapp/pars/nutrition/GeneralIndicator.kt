package com.example.fitnessapp.pars.nutrition


import com.google.gson.annotations.SerializedName

data class GeneralIndicator(
    @SerializedName("carbohydrates")
    val carbohydrates: Double,
    @SerializedName("fats")
    val fats: Double,
    @SerializedName("kkal")
    val kkal: Double,
    @SerializedName("proteins")
    val proteins: Double,
    @SerializedName("water")
    val water: Double
)