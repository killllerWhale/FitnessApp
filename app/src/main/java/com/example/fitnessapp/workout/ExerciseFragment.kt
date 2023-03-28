package com.example.fitnessapp.workout

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.DialogExerciseBinding
import com.example.fitnessapp.databinding.FragmentExerciseBinding
import com.example.fitnessapp.pars.training.Exercise
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding
    private lateinit var bindingDialog: DialogExerciseBinding
    private lateinit var result: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startParsing()

        bindingDialog = DialogExerciseBinding.inflate(layoutInflater)
        val context = requireContext()
        val dialog = Dialog(context)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bindingDialog.root)

        val prefs = context.getSharedPreferences("themes", Context.MODE_PRIVATE)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.exercise)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, Exercise::class.java)
        val exercise = post.exercise[prefs!!.getInt("training", 0)]

        bindingDialog.addExerciseButton.setOnClickListener {
            val kkal = exercise.expenditure * bindingDialog.editText.text.toString()
                .toInt() * bindingDialog.editText2.text.toString().toInt() * 80
            if (exercise.type == 0) {
                result = exercise.name + "^ подходов: " + bindingDialog.editText2.text.toString() + ", повторений: " + bindingDialog.editText.text.toString() + "^ сожжено: " + kkal.toString() + " ккал"
            } else {
                result = exercise.name + "^ подходов: " + bindingDialog.editText2.text.toString() + ", время (мин): " + bindingDialog.editText.text.toString() + "^ сожжено: " + kkal.toString() + " ккал"
            }
            val existingResult = prefs.getString("trainingStorage", "")

            if (existingResult.isNullOrEmpty()) {
                prefs.edit()?.putString("trainingStorage", result)?.apply()
            } else {
                val updatedResult = "$existingResult;$result"
                prefs.edit()?.putString("trainingStorage", updatedResult)?.apply()
            }
            dialog.dismiss()
        }

        loadFragment(DescriptionExerciseFragment())
        binding.description.setBackgroundResource(R.drawable.choose_purple_corners)
        binding.buttonAddExecution.setOnClickListener {
            if (exercise.type == 1) {
                bindingDialog.editText.hint = "время (мин)"
                bindingDialog.editText.requestLayout()
            }
            dialog.show()
        }

        binding.description.setOnClickListener {
            loadFragment(DescriptionExerciseFragment())
            binding.videoInstruction.setBackgroundResource(R.drawable.color_background_gray_corners)
            binding.description.setBackgroundResource(R.drawable.choose_purple_corners)
        }

        binding.videoInstruction.setOnClickListener {
            loadFragment(VideoInstructionFragment())
            binding.videoInstruction.setBackgroundResource(R.drawable.choose_purple_corners)
            binding.description.setBackgroundResource(R.drawable.color_background_gray_corners)
        }

        binding.goBack.setOnClickListener {
            val newFragment = AddWorkoutFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, newFragment)
            transaction.commit()
        }

    }

    private fun startParsing() {
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        var gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.exercise)))
        val inputString = bufferedReader.use { it.readText() }
        var post = gson.fromJson(inputString, Exercise::class.java)
        binding.nameExercise.text = post.exercise[prefs!!.getInt("training", 0)].name
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_exercise, fragment)
        transaction.commit()
    }
}