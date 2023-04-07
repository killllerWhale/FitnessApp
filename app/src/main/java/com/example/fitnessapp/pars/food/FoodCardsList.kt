package com.example.fitnessapp.pars.food


import com.google.gson.annotations.SerializedName

data class FoodCardsList(
    @SerializedName("food")
    val food: List<FoodParameters>
)