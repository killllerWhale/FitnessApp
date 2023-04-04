package com.example.fitnessapp.food

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentFoodOneBinding


class FoodOneFragment : Fragment() {

    lateinit var binding: FragmentFoodOneBinding

    lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //присваиваем актуальную дату
        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        defaultMaxProgress()
        updateAllProgress()
        loadFragment(FoodOneRecommendationsFragment())
        binding.recommendationButton.setBackgroundResource(R.drawable.choose_purple_corners)
        binding.recommendationButton.setOnClickListener {
            loadFragment(FoodOneRecommendationsFragment())
            binding.recommendationButton.setBackgroundResource(R.drawable.choose_purple_corners)
            binding.consumerButton.setBackgroundResource(R.drawable.color_background_teal_700_corners)
        }

        binding.consumerButton.setOnClickListener {
            loadFragment(FoodOneConsumedFragment())
            binding.consumerButton.setBackgroundResource(R.drawable.choose_purple_corners)
            binding.recommendationButton.setBackgroundResource(R.drawable.color_background_teal_700_corners)
        }
    }

    private fun defaultMaxProgress(){
        val maxProgress = prefs.getString("max_progress", "1000;300;500;60")
        if (!maxProgress.isNullOrEmpty()){
            val items = maxProgress.split(";")
            binding.mustHaveKallToday.text = items[0]
            binding.mustHaveCarbToday.text = items[1]
            binding.mustHaveProteinToday.text = items[2]
            binding.mustHaveFatsToday.text = items[3]
            binding.progressAllFood.max = items[0].toInt()
            binding.progressBarCarbohydrates.max = items[1].toInt()
            binding.progressBarProtein.max = items[2].toInt()
            binding.progressBarFats.max = items[3].toInt()
        }
    }

    private fun updateAllProgress(){
        val foodProgress = prefs.getString("food_info", "0;0;0;0")
        val food = foodProgress!!.split(";")
        updateProgressBar(food[0])
        updateProgressBarFats(food[3])
        updateProgressBarProtein(food[2])
        updateProgressBarCarbohydrates(food[1])
    }

    private fun updateProgressBar(s: String) {
        binding.haveKkalToday.text = s
        binding.progressAllFood.progress = s.toInt()
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgressBarFats(s: String) {
        binding.haveFatsToday.text = s
        binding.progressBarFats.progress = s.toInt()
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgressBarProtein(s: String) {
        binding.haveProteinToday.text = s
        binding.progressBarProtein.progress = s.toInt()
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgressBarCarbohydrates(s: String) {
        binding.haveCarbToday.text = s
        binding.progressBarCarbohydrates.progress = s.toInt()
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_food_content, fragment)
        transaction.commit()
    }

}