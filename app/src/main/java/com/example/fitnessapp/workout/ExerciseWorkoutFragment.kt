package com.example.fitnessapp.workout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentExerciseWorkoutBinding
import com.example.fitnessapp.pars.traningRecommendation.TrainingRecommendationsList
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class ExerciseWorkoutFragment : Fragment() {

    private lateinit var binding: FragmentExerciseWorkoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseWorkoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFragment(DescriptionRecommendationFragment())
        binding.description.setBackgroundResource(R.drawable.choose_purple_corners)

        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        val position = prefs.getInt("position", 0)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.workout_recommendation)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, TrainingRecommendationsList::class.java)

        val target = prefs.getInt("user_target", 0)
        when (target) {
            0 -> {
                val plan = post.plan[0].losingWeight
                binding.nameExercise2.text = plan[position].name
            }
            1 -> {
                val plan = post.plan[1].maintenance
                binding.nameExercise2.text = plan[position].name
            }
            2 -> {
                val plan = post.plan[2].weightGain
                binding.nameExercise2.text = plan[position].name
            }
        }

        binding.workoutGo.setOnClickListener {
            loadFragment(TrainingRecommendationFragment())
            binding.description.setBackgroundResource(R.drawable.color_background_gray_corners)
            binding.workoutGo.setBackgroundResource(R.drawable.choose_purple_corners)
        }

        binding.description.setOnClickListener {
            loadFragment(DescriptionRecommendationFragment())
            binding.description.setBackgroundResource(R.drawable.choose_purple_corners)
            binding.workoutGo.setBackgroundResource(R.drawable.color_background_gray_corners)
        }

        binding.goBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, TrainingFragment())
                .commit()
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_exercise_recom, fragment)
        transaction.commit()
    }
}