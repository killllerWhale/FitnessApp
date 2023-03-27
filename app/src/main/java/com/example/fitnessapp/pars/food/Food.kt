package com.example.fitnessapp.pars.food


import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("food")
    val food: List<FoodX>
)