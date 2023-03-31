package com.example.fitnessapp.workout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentDescriptionRecommendationBinding
import com.example.fitnessapp.pars.traningRecommendation.plan
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class DescriptionRecommendationFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionRecommendationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionRecommendationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        var position = prefs.getInt("position", 0)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.workout_recommendation)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, plan::class.java)
        val plan = post.plan[0].losingWeight

        binding.mechanicsDescription.text = plan[position].description
    }
}
