package com.example.fitnessapp.workout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentAddWorkoutBinding

class AddWorkoutFragment : Fragment() {

    private lateinit var binding: FragmentAddWorkoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddWorkoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        binding.goBack.setOnClickListener{
            loadFragment(TrainingFragment())
        }

        binding.let { b ->
            listOf(
                b.trainingBar,
                b.trainingBridge,
                b.trainingLunges,
                b.trainingLifts,
                b.trainingPullUps,
                b.trainingPushUps,
                b.trainingRunning,
                b.trainingSideBar,
                b.trainingSkaklka,
                b.trainingSquat,
                b.trainingToeLifts,
                b.trainingWalking
            ).forEach { button ->
                button.setOnClickListener {
                    when (button.id) {
                        R.id.training_bar -> {
                            prefs.edit().putInt("training", 0).apply()
                        }
                        R.id.training_bridge -> {
                            prefs.edit().putInt("training", 1).apply()
                        }
                        R.id.training_lunges -> {
                            prefs.edit().putInt("training", 2).apply()
                        }
                        R.id.training_lifts -> {
                            prefs.edit().putInt("training", 3).apply()
                        }
                        R.id.training_pull_ups -> {
                            prefs.edit().putInt("training", 4).apply()
                        }
                        R.id.training_push_ups -> {
                            prefs.edit().putInt("training", 5).apply()
                        }
                        R.id.training_running -> {
                            prefs.edit().putInt("training", 6).apply()
                        }
                        R.id.training_side_bar -> {
                            prefs.edit().putInt("training", 7).apply()
                        }
                        R.id.training_skaklka -> {
                            prefs.edit().putInt("training", 8).apply()
                        }
                        R.id.training_squat -> {
                            prefs.edit().putInt("training", 9).apply()
                        }
                        R.id.training_toe_lifts -> {
                            prefs.edit().putInt("training", 10).apply()
                        }
                        R.id.training_walking -> {
                            prefs.edit().putInt("training", 11).apply()
                        }
                    }
                    loadFragment(ExerciseFragment())
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
