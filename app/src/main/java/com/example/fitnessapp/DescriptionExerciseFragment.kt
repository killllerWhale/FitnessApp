package com.example.fitnessapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.databinding.FragmentDescriptionExerciseBinding
import com.example.fitnessapp.pars.training.Exercise
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class DescriptionExerciseFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionExerciseBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startParsing()

    }
    @SuppressLint("SetTextI18n")
    private fun startParsing(){
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        var gson = Gson()
        val bufferedReader = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.exercise)))
        val inputString = bufferedReader.use { it.readText() }
        var post = gson.fromJson(inputString, Exercise::class.java)
        val exercise = post.exercise[prefs!!.getInt("training", 0)]
        if (exercise.type == 0) {
            binding.kkalBurned.text = "За каждый подход из 15 повторений будет сожжено калорий: " + (exercise.expenditure * 15).toString()
        }else{
//            binding.kkalBurned.text = binding.calculationWeightCol.text.toString().toInt()
        }
//        (binding.calculationWeightCol.text.toString().toInt()
        binding.mechanicsDescription.text = post.exercise[prefs!!.getInt("training", 0)].mechanics
        binding.benefitsOfExerciseDescription.text = post.exercise[prefs.getInt("training", 0)].description
    }
}