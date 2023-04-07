package com.example.fitnessapp.food

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentFoodOneBinding
import com.example.fitnessapp.pars.nutrition.Recomend
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader


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


    private fun defaultMaxProgress() {
        val target = prefs.getInt("user_target", 0)
        val dataToday = prefs.getInt("data_today", 0)

        val weightUser = prefs.getString("user_weight", "0").toString().toInt()
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.nutrition)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, Recomend::class.java)


        @Suppress("IMPLICIT_CAST_TO_ANY")
        when (dataToday) {
            0 -> {
                val recommendation = post.nutrition[target].breakfast[0]
                with(binding) {
                    mustHaveKallToday.text = (recommendation.kkal * weightUser).toInt().toString()
                    mustHaveCarbToday.text =
                        (recommendation.carbohydrates * weightUser).toInt().toString()
                    mustHaveProteinToday.text =
                        (recommendation.proteins * weightUser).toInt().toString()
                    mustHaveFatsToday.text = (recommendation.fats * weightUser).toInt().toString()
                    progressAllFood.max = (recommendation.kkal * weightUser).toInt()
                    progressBarCarbohydrates.max = (recommendation.carbohydrates * weightUser).toInt()
                    progressBarProtein.max = (recommendation.proteins * weightUser).toInt()
                    progressBarFats.max = (recommendation.fats * weightUser).toInt()
                }
            }
            1 -> {
                val recommendation = post.nutrition[target].lunch[0]
                with(binding) {
                    mustHaveKallToday.text = (recommendation.kkal * weightUser).toInt().toString()
                    mustHaveCarbToday.text =
                        (recommendation.carbohydrates * weightUser).toInt().toString()
                    mustHaveProteinToday.text =
                        (recommendation.proteins * weightUser).toInt().toString()
                    mustHaveFatsToday.text = (recommendation.fats * weightUser).toInt().toString()
                    progressAllFood.max = (recommendation.kkal * weightUser).toInt()
                    progressBarCarbohydrates.max = (recommendation.carbohydrates * weightUser).toInt()
                    progressBarProtein.max = (recommendation.proteins * weightUser).toInt()
                    progressBarFats.max = (recommendation.fats * weightUser).toInt()
                }
            }
            2 -> {
                val recommendation = post.nutrition[target].supper[0]
                with(binding) {
                    mustHaveKallToday.text =
                        (recommendation.kkal * weightUser).toInt().toString()
                    mustHaveCarbToday.text =
                        (recommendation.carbohydrates * weightUser).toInt().toString()
                    mustHaveProteinToday.text =
                        (recommendation.proteins * weightUser).toInt().toString()
                    mustHaveFatsToday.text =
                        (recommendation.fats * weightUser).toInt().toString()
                    progressAllFood.max = (recommendation.kkal * weightUser).toInt()
                    progressBarCarbohydrates.max =
                        (recommendation.carbohydrates * weightUser).toInt()
                    progressBarProtein.max = (recommendation.proteins * weightUser).toInt()
                    progressBarFats.max = (recommendation.fats * weightUser).toInt()
                }
            }
        }
    }


    private fun updateAllProgress() {
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
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_food, fragment)
            .commit()
    }

}