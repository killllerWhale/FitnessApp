package com.example.fitnessapp.food

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.adapter.FoodAdapter
import com.example.fitnessapp.databinding.FragmentFoodOneConsumedBinding
import com.example.fitnessapp.model.FoodModel
import java.util.*
import kotlin.collections.ArrayList

class FoodOneConsumedFragment : Fragment() {

    lateinit var binding: FragmentFoodOneConsumedBinding
    private lateinit var existingResult: String
    private var adapter = FoodAdapter{}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFoodOneConsumedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        existingResult = prefs!!.getString("foodStorage", "").toString()

        val moscowTimeZone = TimeZone.getTimeZone("Europe/Moscow")
        val calendar = Calendar.getInstance(moscowTimeZone)
        val currentDate = calendar.get(Calendar.DAY_OF_YEAR)
        val lastUpdated = prefs.getInt("lastUpdated", 0)

        if (existingResult.isEmpty() || currentDate > lastUpdated) {
            binding.textViewNotFood.text = "Вы не съели ни одного продукта сегодня"
        } else {
            initial()
        }
    }

    private fun initial() {
        binding.rvFood.adapter = adapter
        adapter.setList(myFood())
    }

    private fun myFood(): ArrayList<FoodModel> {
        val items = existingResult.split(";")
        val foodList = ArrayList<FoodModel>()

        items.forEach { item ->
            val values = item.split("^")
            val name = values[0]
            val gram = values[1]
            val kkal = values[2]

            val foodModel = FoodModel(name, gram, kkal)
            foodList.add(foodModel)
        }

        return foodList
    }
}
