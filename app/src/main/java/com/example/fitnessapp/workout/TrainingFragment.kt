package com.example.fitnessapp.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentTrainingBinding

class TrainingFragment : Fragment() {

    lateinit var binding: FragmentTrainingBinding

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
        updateProgressBar()


        loadFragment(WorkoutRecommendationFragment())
        binding.workoutRecommendation.setBackgroundResource(R.drawable.choose_purple_corners)
        binding.buttonAddWorkout.setOnClickListener{
            val newFragment = AddWorkoutFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
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
        progressBar.progress = 10
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_training, fragment)
        transaction.commit()
    }

}