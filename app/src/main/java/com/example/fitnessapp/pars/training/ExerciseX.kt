package com.example.fitnessapp.pars.training


import com.google.gson.annotations.SerializedName

data class ExerciseX(
    @SerializedName("description")
    val description: String,
    @SerializedName("expenditure")
    val expenditure: Double,
    @SerializedName("mechanics")
    val mechanics: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: Int
)