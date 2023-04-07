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
import com.example.fitnessapp.adapter.TraningRecomAdapter
import com.example.fitnessapp.databinding.FragmentWorkoutRecommendationBinding
import com.example.fitnessapp.model.TraningRecomModel
import com.example.fitnessapp.pars.traningRecommendation.plan
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class WorkoutRecommendationFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutRecommendationBinding
    private lateinit var adapter: TraningRecomAdapter
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
        adapter = TraningRecomAdapter { position ->
            prefs.edit().putInt("position", position).apply()
            val newFragment = ExerciseWorkoutFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.rvTraningRec.adapter = adapter
        adapter.setList(myTraning())
    }

    private fun myTraning(): ArrayList<TraningRecomModel> {
        val target = prefs.getInt("user_target", 0)

        val gson = Gson()
        val bufferedReader = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.workout_recommendation)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, plan::class.java)

        val traningList = ArrayList<TraningRecomModel>()
        when (target) {
            0 -> {
                traningList.add(TraningRecomModel(post.plan[0].losingWeight[0].name,(post.plan[0].losingWeight[0].kkal.toInt()* prefs.getString("user_weight", "0")!!
                    .toInt()).toString()))
                traningList.add(TraningRecomModel(post.plan[0].losingWeight[1].name,(post.plan[0].losingWeight[1].kkal.toInt()*prefs.getString("user_weight", "0")!!.toInt()).toString()))
            }
            1 -> {
                traningList.add(TraningRecomModel(post.plan[1].maintenance[0].name,(post.plan[1].maintenance[0].kkal.toInt()*prefs.getString("user_weight", "0")!!.toInt()).toString()))
                traningList.add(TraningRecomModel(post.plan[1].maintenance[1].name,(post.plan[1].maintenance[1].kkal.toInt()*prefs.getString("user_weight", "0")!!.toInt()).toString()))
            }
            2 -> {
                traningList.add(TraningRecomModel(post.plan[2].weightGain[0].name,(post.plan[2].weightGain[0].kkal.toInt()*prefs.getString("user_weight", "0")!!.toInt()).toString()))
                traningList.add(TraningRecomModel(post.plan[2].weightGain[1].name,(post.plan[2].weightGain[1].kkal.toInt()*prefs.getString("user_weight", "0")!!.toInt()).toString()))
            }
        }

        return traningList
    }
}