package com.example.fitnessapp.pars.nutrition


import com.google.gson.annotations.SerializedName

data class Nutrition(
    @SerializedName("breakfast")
    val breakfast: List<Breakfast>,
    @SerializedName("general_indicators")
    val generalIndicators: List<GeneralIndicator>,
    @SerializedName("Lunch")
    val lunch: List<Lunch>,
    @SerializedName("Snack")
    val snack: List<Snack>,
    @SerializedName("Supper")
    val supper: List<Supper>
)