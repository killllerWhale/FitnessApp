package com.example.fitnessapp.workout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.FoodAdapter
import com.example.fitnessapp.databinding.FragmentTrainingRecommendationBinding
import com.example.fitnessapp.model.FoodModel
import com.example.fitnessapp.pars.traningRecommendation.TrainingRecommendationsList
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class TrainingRecommendationFragment : Fragment() {

    private var adapter = FoodAdapter {}
    private lateinit var binding: FragmentTrainingRecommendationBinding
    private lateinit var prefs: SharedPreferences
    private var result = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingRecommendationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        initial()
        binding.endTraining.setOnClickListener {
            val caloriesBurned = prefs.getString("caloriesBurned", "0")
            prefs.edit()?.putString(
                "caloriesBurned", ((result * prefs.getString("user_weight", "0")!!
                    .toInt()) + caloriesBurned!!.toInt()).toString()
            )?.apply()

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, TrainingFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initial() {
        binding.rvTraningRecom.adapter = adapter
        adapter.setList(myTraning())
    }

    private fun myTraning(): ArrayList<FoodModel> {
        val position = prefs.getInt("position", 0)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.workout_recommendation)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, TrainingRecommendationsList::class.java)

        when (prefs.getInt("user_target", 0)) {
            0 -> {
                val items = post.plan[0].losingWeight[position].workout
                val trainingList = ArrayList<FoodModel>()
                items.forEach { item ->
                    val name = item.name
                    val gram =
                        if (item.type == 0) getString(R.string.number_of_repetitions) + ": " + item.coll.toString() else getString(
                            R.string.number_of_repetitions_time
                        ) + ": " + item.coll.toString() + " мин"
                    val kkal =
                        getString(R.string.number_of_approaches) + ": " + item.collB.toString()

                    val foodModel = FoodModel(name, gram, kkal)
                    trainingList.add(foodModel)
                }
                result = post.plan[0].losingWeight[position].kkal.toInt()
                return trainingList
            }
            1 -> {
                val items = post.plan[1].maintenance[position].workout
                val trainingList = ArrayList<FoodModel>()
                items.forEach { item ->
                    val name = item.name
                    val gram =
                        if (item.type == 0) getString(R.string.number_of_repetitions) + ": " + item.coll.toString() else getString(
                            R.string.number_of_repetitions_time
                        ) + ": " + item.coll.toString() + " мин"
                    val kkal =
                        getString(R.string.number_of_approaches) + ": " + item.collB.toString()
                    val foodModel = FoodModel(name, gram, kkal)
                    trainingList.add(foodModel)
                }
                result = post.plan[1].maintenance[position].kkal.toInt()
                return trainingList
            }
            else -> {
                val items = post.plan[2].weightGain[position].workout
                val trainingList = ArrayList<FoodModel>()
                items.forEach { item ->
                    val name = item.name
                    val gram =
                        if (item.type == 0) getString(R.string.number_of_repetitions) + ": " + item.coll.toString() else getString(
                            R.string.number_of_repetitions_time
                        ) + ": " + item.coll.toString() + " мин"
                    val kkal =
                        getString(R.string.number_of_approaches) + ": " + item.collB.toString()
                    val foodModel = FoodModel(name, gram, kkal)
                    trainingList.add(foodModel)
                }
                result = post.plan[2].weightGain[position].kkal.toInt()
                return trainingList
            }
        }
    }

}
