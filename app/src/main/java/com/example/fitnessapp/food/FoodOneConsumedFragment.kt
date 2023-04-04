package com.example.fitnessapp.food

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.adapter.foodAdapter
import com.example.fitnessapp.databinding.FragmentFoodOneConsumedBinding
import com.example.fitnessapp.model.foodModel
import com.example.fitnessapp.model.traningModel
import java.util.*
import kotlin.collections.ArrayList

class FoodOneConsumedFragment : Fragment() {

    lateinit var binding: FragmentFoodOneConsumedBinding
    private lateinit var existingResult: String
    private lateinit var adapter: foodAdapter
    private lateinit var recyclerView: RecyclerView

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
        recyclerView = binding.rvFood
        adapter = foodAdapter()
        recyclerView.adapter = adapter
        adapter.setList(myFood())
    }

    private fun myFood(): ArrayList<foodModel> {
        val items = existingResult.split(";")
        val foodList = ArrayList<foodModel>()

        items.forEach { item ->
            val values = item.split("^")
            val name = values[0]
            val gram = values[1]
            val kkal = values[2]

            val foodModel = foodModel(name, gram, kkal)
            foodList.add(foodModel)
        }

        return foodList
    }
}
