package com.example.fitnessapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FragmentFoodBinding
import com.example.fitnessapp.databinding.FragmentFoodTwoBinding
import java.util.*

class FoodFragment : Fragment() {

    lateinit var binding: FragmentFoodBinding

    lateinit var listFragment: FoodTwoFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(layoutInflater, container, false)
        // Load the FoodTwoFragment into the container_food FrameLayout
        listFragment = FoodTwoFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.container_food, listFragment)
            .commit()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFragment(FoodOneFragment())
        loadDataEat()

        // Show the FoodTwoFragment when the search view is clicked
        binding.productSearch.setOnSearchClickListener {
            loadFragment(FoodTwoFragment())
        }

        // Update the list in FoodTwoFragment based on search query
        binding.productSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                listFragment.updateList(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listFragment.updateList(newText)
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
            loadFragment(FoodOneFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_food, fragment)
        transaction.commit()
    }

    private fun loadDataEat() {
        val currentTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"))
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
        System.out.println(currentHour)

        val textView = binding.textFoodDataNow

        when (currentHour) {
            in 6..10 -> textView.text = "Завтрак"
            in 11..16 -> textView.text = "Обед"
            in 17..23, in 0..5 -> textView.text = "Ужин"
        }

    }
}