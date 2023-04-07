package com.example.fitnessapp.workout

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)

        binding.calculationWeightCol.text = prefs.getString("user_weight", "0")
        val position = prefs.getInt("position", 0)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.workout_recommendation)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, plan::class.java)

        when (prefs.getInt("user_target", 0)) {
            0 -> {
                val plan = post.plan[0].losingWeight
                binding.kkalBurned.text =
                    getString(R.string.Text3) + (plan[position].kkal.toInt() * prefs.getString(
                        "user_weight",
                        "0"
                    )!!.toInt()).toString()
                binding.mechanicsDescription.text = plan[position].description
            }
            1 -> {
                val plan = post.plan[0].maintenance
                binding.mechanicsDescription.text = plan[position].description
                binding.kkalBurned.text =
                    getString(R.string.Text3) + (plan[position].kkal.toInt() * prefs.getString(
                        "user_weight",
                        "0"
                    )!!.toInt()).toString()
            }
            2 -> {
                val plan = post.plan[0].weightGain
                binding.mechanicsDescription.text = plan[position].description
                binding.kkalBurned.text =
                    getString(R.string.Text3) + (plan[position].kkal.toInt() * prefs.getString(
                        "user_weight",
                        "0"
                    )!!.toInt()).toString()
            }
        }

    }
}
