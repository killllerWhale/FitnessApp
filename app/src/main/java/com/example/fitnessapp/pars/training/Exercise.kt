package com.example.fitnessapp.pars.training


import com.google.gson.annotations.SerializedName

data class Exercise(
    @SerializedName("exercise")
    val exercise: List<ExerciseX>
)