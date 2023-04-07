package com.example.fitnessapp.food

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentFoodBinding
import com.example.fitnessapp.pars.nutrition.Recomend
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class FoodFragment : Fragment() {

    lateinit var binding: FragmentFoodBinding
    lateinit var listFragmentTwo: FoodTwoFragment
    lateinit var prefs: SharedPreferences
    private lateinit var listFragmentCard: FoodCardFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(layoutInflater, container, false)
        // Load the FoodTwoFragment into the container_food FrameLayout
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        loadDataEat()
        if (prefs.getString("food_position", "") != "") {
            loadFragment(FoodCardFragment())
            listFragmentCard = FoodCardFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.container_food, listFragmentCard)
                .commit()
        } else {
            loadFragment(FoodOneFragment())
        }


        binding.goBack.setOnClickListener {
            loadFragment(FoodOneFragment())
        }

        // Show the FoodTwoFragment when the search view is clicked
        binding.productSearch.setOnSearchClickListener {
            loadFragment(FoodTwoFragment())
            listFragmentTwo = FoodTwoFragment()
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.container_food, listFragmentTwo)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Update the list in FoodTwoFragment based on search query
        binding.productSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                listFragmentTwo.updateList(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != "") {
                    listFragmentTwo.updateList(newText)
                }
                return false
            }
        })

        // Hide the search view and show FoodOneFragment when the cancel button is clicked
        binding.buttonCancel.setOnClickListener {
            binding.productSearch.apply {
                setQuery("", false)
                isIconified = true
            }
            binding.productSearch.clearFocus()
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.productSearch.windowToken, 0)
            prefs.edit().putString("food_position", "").apply()
            prefs.edit().putInt("gram", 100).apply()
            loadParentFragment(FoodFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_food, fragment)
            .commit()
    }

    private fun loadParentFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun loadDataEat() {
        val currentTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"))
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)

        val textView = binding.textFoodDataNow

        when (currentHour) {
            in 6..10 -> {
                textView.text = "Завтрак"
                prefs.edit().putInt("data_today", 0).apply()
            }
            in 11..16 -> {
                textView.text = "Обед"
                prefs.edit().putInt("data_today", 1).apply()
            }
            in 17..23, in 0..5 -> {
                textView.text = "Ужин"
                prefs.edit().putInt("data_today", 2).apply()
            }
        }
    }
}