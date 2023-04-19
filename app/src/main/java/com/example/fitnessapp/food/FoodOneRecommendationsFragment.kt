package com.example.fitnessapp.food

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.FoodRecomAdapter
import com.example.fitnessapp.databinding.FragmentFoodOneRecommendationsBinding
import com.example.fitnessapp.model.FoodRecomModel
import com.example.fitnessapp.pars.nutrition.Recomend
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class FoodOneRecommendationsFragment : Fragment() {

    lateinit var binding: FragmentFoodOneRecommendationsBinding
    lateinit var prefs: SharedPreferences
    private lateinit var adapter: FoodRecomAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFoodOneRecommendationsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        val target = prefs.getInt("user_target", 0)
        val dataToday = prefs.getInt("data_today", 0)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.nutrition)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, Recomend::class.java)
        initial()
        when (dataToday) {
            0 -> {
                val recommendation = post.nutrition[target].breakfast[0]
                adapter.setList(myFood(recommendation.recommendations))
            }
            1 -> {
                val recommendation = post.nutrition[target].lunch[0]
                adapter.setList(myFood(recommendation.recommendations))
            }
            2 -> {
                val recommendation = post.nutrition[target].supper[0]
                adapter.setList(myFood(recommendation.recommendations))
            }
            3 -> {
                val recommendation = post.nutrition[target].snack[0]
                adapter.setList(myFood(recommendation.recommendations))
            }
        }
    }

    private fun initial() {
        adapter = FoodRecomAdapter()
        binding.rvFood.adapter = adapter
    }

    private fun myFood(recom : List<String>): ArrayList<FoodRecomModel> {
        val foodList = ArrayList<FoodRecomModel>()
        recom.forEach { item ->
        val values = item.split("^")
        val name = values[0]

        val foodModel = FoodRecomModel(name)
        foodList.add(foodModel)
        }
        return foodList
    }
}