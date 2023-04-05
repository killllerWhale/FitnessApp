package com.example.fitnessapp.pars.nutrition


import com.google.gson.annotations.SerializedName

data class Recomend(
    @SerializedName("nutrition")
    val nutrition: List<Nutrition>
)