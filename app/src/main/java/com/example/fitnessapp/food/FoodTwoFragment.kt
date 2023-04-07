package com.example.fitnessapp.food

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentFoodTwoBinding
import com.example.fitnessapp.pars.food.FoodCardsList
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList

class FoodTwoFragment : Fragment() {

    private lateinit var binding: FragmentFoodTwoBinding

    private lateinit var foodList: ArrayList<String>

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
        createList()
        foodListAdapter =
            ArrayAdapter(requireContext(), R.layout.items_list_view_color, R.id.text_view, foodList)
        binding.listOfFood.adapter = foodListAdapter
        binding.listOfFood.setOnItemClickListener { parent, view, position, id ->
            val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
            prefs!!.edit().putString("food_position", foodList[position]).apply()
            prefs.edit().putInt("gram", 100).apply()
            loadFragment(FoodFragment())
        }
    }

    fun updateList(query: String?) {
        foodList = ArrayList()
        createList()
        val filteredData = if (query.isNullOrEmpty() || query == "") {
            foodList
        } else {
            foodList.filter { it.contains(query, ignoreCase = true) }
        }
        foodListAdapter.clear()
        foodListAdapter.addAll(filteredData)
        foodList = filteredData as ArrayList<String>
        foodListAdapter.notifyDataSetChanged()
    }

    private fun createList(){
        val gson = Gson()
        val bufferedReader = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.product_contents)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, FoodCardsList::class.java)
        for (i in 0..66){
            foodList.add(post.food[i].name)
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_food, fragment)
        transaction.commit()
    }
}