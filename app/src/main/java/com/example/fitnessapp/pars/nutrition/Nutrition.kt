package com.example.fitnessapp.pars.nutrition


import com.google.gson.annotations.SerializedName

data class Nutrition(
    @SerializedName("breakfast")
    val breakfast: List<Breakfast>,
    @SerializedName("general_indicators")
    val generalIndicators: List<GeneralIndicator>,
    @SerializedName("lunch")
    val lunch: List<Lunch>,
    @SerializedName("snack")
    val snack: List<Snack>,
    @SerializedName("supper")
    val supper: List<Supper>
)