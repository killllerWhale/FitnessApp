package com.example.fitnessapp.workout


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentTrainingBinding
import com.example.fitnessapp.pars.traningRecommendation.TrainingRecommendationsList
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class TrainingFragment : Fragment() {

    lateinit var binding: FragmentTrainingBinding
    lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTrainingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        myTraning()
        updateProgressBar()
        loadFragment(WorkoutRecommendationFragment())
        binding.workoutRecommendation.setBackgroundResource(R.drawable.choose_purple_corners)
        binding.buttonAddWorkout.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddWorkoutFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.myTraining.setOnClickListener{
            loadFragment(MyTrainingFragment())
            binding.workoutRecommendation.setBackgroundResource(R.drawable.color_background_gray_corners)
            binding.myTraining.setBackgroundResource(R.drawable.choose_purple_corners)
        }

        binding.workoutRecommendation.setOnClickListener{
            loadFragment(WorkoutRecommendationFragment())
            binding.workoutRecommendation.setBackgroundResource(R.drawable.choose_purple_corners)
            binding.myTraining.setBackgroundResource(R.drawable.color_background_gray_corners)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Ваш код обработки нажатия на кнопку назад
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun updateProgressBar() {
        val progressBar: ProgressBar = binding.progressBar
        val caloriesBurned = prefs.getString("caloriesBurned", "0")
        progressBar.progress = caloriesBurned!!.toInt()
        binding.kcal.text = caloriesBurned
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_training, fragment)
        transaction.commit()
    }

    private fun myTraning() {
        val position = prefs.getInt("position", 0)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.workout_recommendation)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, TrainingRecommendationsList::class.java)
        val target = prefs.getInt("user_target", 0)
        val weightUser = prefs.getString("user_weight", "0").toString().toInt()

        when (target) {
            0 -> {
                binding.mustDo.text = (post.plan[0].losingWeight[position].kkal.toInt() * weightUser).toString()
                binding.progressBar.max = post.plan[0].losingWeight[position].kkal.toInt() * weightUser
            }
            1 -> {
                binding.mustDo.text = (post.plan[0].maintenance[position].kkal.toInt() * weightUser).toString()
                binding.progressBar.max = post.plan[0].maintenance[position].kkal.toInt() * weightUser
            }
            else -> {
                binding.mustDo.text = (post.plan[0].weightGain[position].kkal.toInt() * weightUser).toString()
                binding.progressBar.max = post.plan[0].weightGain[position].kkal.toInt() * weightUser
            }
        }
    }

}