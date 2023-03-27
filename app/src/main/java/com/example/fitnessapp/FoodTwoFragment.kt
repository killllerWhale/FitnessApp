package com.example.fitnessapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.fitnessapp.databinding.FragmentFoodTwoBinding
import java.util.ArrayList

class FoodTwoFragment : Fragment() {

    private lateinit var binding: FragmentFoodTwoBinding

    private lateinit var foodList: ArrayList<String>;

    private lateinit var foodListAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFoodTwoBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodList = ArrayList()
        foodList.add("C")
        foodList.add("C#")
        foodList.add("Java")
        foodList.add("Javascript")
        foodList.add("Python")
        foodList.add("Dart")
        foodList.add("Kotlin")
        foodList.add("Typescript")
        foodListAdapter =
            ArrayAdapter(requireContext(), R.layout.items_list_view_color, R.id.text_view, foodList)
        binding.listOfFood.adapter = foodListAdapter
    }

    fun updateList(query: String?) {
        val filteredData = if (query.isNullOrEmpty() || query == "") {
            foodList
        } else {
            foodList.filter { it.contains(query, ignoreCase = true) }
        }
        Log.d("TAG", query.toString())
        Log.d("TAG", foodList.toString())
        foodListAdapter.clear()
        foodListAdapter.addAll(filteredData)
        foodListAdapter.notifyDataSetChanged()
    }
}