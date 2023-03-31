package com.example.fitnessapp.workout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.SettingsFragment
import com.example.fitnessapp.adapter.TraningRecomAdapter
import com.example.fitnessapp.adapter.traningAdapter
import com.example.fitnessapp.databinding.FragmentWorkoutRecommendationBinding
import com.example.fitnessapp.model.TraningRecomModel
import com.example.fitnessapp.model.traningModel
import com.example.fitnessapp.pars.training.Exercise
import com.example.fitnessapp.pars.traningRecommendation.plan
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class WorkoutRecommendationFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutRecommendationBinding
    private lateinit var adapter: TraningRecomAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutRecommendationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        initial()

    }

    private fun initial() {
        recyclerView = binding.rvTraningRec
        adapter = TraningRecomAdapter { position ->
            prefs.edit().putInt("position", position).apply()
            val newFragment = ExerciseWorkoutFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        recyclerView.adapter = adapter
        adapter.setList(myTraning())
    }

    private fun myTraning(): ArrayList<TraningRecomModel> {
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.workout_recommendation)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, plan::class.java)
        val plan = post.plan[0].losingWeight
        var one = TraningRecomModel(plan[0].name,"800 ккал")
        var two = TraningRecomModel(plan[1].name,"960 ккал")
        val traningList = ArrayList<TraningRecomModel>()
        traningList.add(one)
        traningList.add(two)
        return traningList
    }
}