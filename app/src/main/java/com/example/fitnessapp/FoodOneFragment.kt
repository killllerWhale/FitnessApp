package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.fitnessapp.databinding.FragmentFoodOneBinding
import com.example.fitnessapp.databinding.FragmentTrainingBinding


class FoodOneFragment : Fragment() {

    lateinit var binding: FragmentFoodOneBinding

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
        updateProgressBar()
        updateProgressBarFats()
        updateProgressBarSquirrels()
        updateProgressBarCarbohydrates()
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

    private fun updateProgressBar() {
        val progressBar: ProgressBar = binding.progressAllFood
        progressBar.progress = 50
    }

    private fun updateProgressBarFats() {
        val progressBar: ProgressBar = binding.progressBarFats
        progressBar.progress = 50
    }

    private fun updateProgressBarSquirrels() {
        val progressBar: ProgressBar = binding.progressBarSquirrels
        progressBar.progress = 25
    }

    private fun updateProgressBarCarbohydrates() {
        val progressBar: ProgressBar = binding.progressBarCarbohydrates
        progressBar.progress = 15
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_food_content, fragment)
        transaction.commit()
    }

}