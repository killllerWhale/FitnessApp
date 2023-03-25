package com.example.fitnessapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.databinding.FragmentAddWorkoutBinding

class AddWorkoutFragment : Fragment(), View.OnClickListener {

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
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        prefs?.edit()?.putString("money", "6")?.apply()
        System.out.println(prefs!!.getString("money", "0"))

        binding.goBack.setOnClickListener{
            loadFragment(TrainingFragment())
        }

        binding.trainingBar.setOnClickListener(this)
        binding.trainingBridge.setOnClickListener(this)
        binding.trainingLunges.setOnClickListener(this)
        binding.trainingLifts.setOnClickListener(this)
        binding.trainingPullUps.setOnClickListener(this)
        binding.trainingPushUps.setOnClickListener(this)
        binding.trainingRunning.setOnClickListener(this)
        binding.trainingSideBar.setOnClickListener(this)
        binding.trainingSkaklka.setOnClickListener(this)
        binding.trainingSquat.setOnClickListener(this)
        binding.trainingToeLifts.setOnClickListener(this)
        binding.trainingWalking.setOnClickListener(this)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.training_bar -> {

            }
            R.id.training_bridge -> {

            }
            R.id.training_lunges -> {

            }
            R.id.training_lifts -> {

            }
            R.id.training_pull_ups -> {

            }
            R.id.training_push_ups -> {

            }
            R.id.training_running -> {

            }
            R.id.training_side_bar -> {

            }
            R.id.training_skaklka -> {

            }
            R.id.training_squat -> {

            }
            R.id.training_toe_lifts -> {

            }
            R.id.training_walking -> {

            }
        }
    }
}