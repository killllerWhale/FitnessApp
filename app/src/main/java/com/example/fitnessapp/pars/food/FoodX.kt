package com.example.fitnessapp.pars.food


import com.google.gson.annotations.SerializedName

data class FoodX(
    @SerializedName("ash")
    val ash: Double,
    @SerializedName("calcium")
    val calcium: Double,
    @SerializedName("carbohydrates")
    val carbohydrates: Double,
    @SerializedName("cholesterol")
    val cholesterol: Double,
    @SerializedName("fats")
    val fats: Double,
    @SerializedName("fiber")
    val fiber: Double,
    @SerializedName("iron")
    val iron: Double,
    @SerializedName("kkal")
    val kkal: Int,
    @SerializedName("magnesium")
    val magnesium: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("proteins")
    val proteins: Double,
    @SerializedName("sakharov")
    val sakharov: Double,
    @SerializedName("water")
    val water: Double
)