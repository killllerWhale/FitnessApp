package com.example.fitnessapp.workout

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
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
import java.util.*

class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding
    private lateinit var bindingDialog: DialogExerciseBinding
    private lateinit var result: String
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingDialog = DialogExerciseBinding.inflate(layoutInflater)
        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)

        //создание диалога
        val dialog = Dialog(requireContext()).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(bindingDialog.root)
        }


        //чтение json
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.exercise)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, Exercise::class.java)
        val exercise = post.exercise[prefs!!.getInt("training", 0)]
        var kkal = 0

        binding.nameExercise.text = exercise.name

        bindingDialog.addExerciseButton.setOnClickListener {
            //обработка инфы с диалога
            val weightUser = prefs.getString("user_weight", "0").toString().toInt()
            if (exercise.type == 0) {
                kkal = (exercise.expenditure * bindingDialog.editText.text.toString()
                    .toInt() * bindingDialog.editText2.text.toString().toInt()).toInt()
                result = exercise.name + "^ подходов: " + bindingDialog.editText2.text.toString() + ", повторений: " + bindingDialog.editText.text.toString() + "^ сожжено: " + kkal.toString() + " ккал"
            } else {
                kkal = (exercise.expenditure * bindingDialog.editText.text.toString()
                    .toInt() * bindingDialog.editText2.text.toString().toInt() * weightUser).toInt()
                result = exercise.name + "^ подходов: " + bindingDialog.editText2.text.toString() + ", время (мин): " + bindingDialog.editText.text.toString() + "^ сожжено: " + kkal.toString() + " ккал"
            }
            val existingResult = prefs.getString("trainingStorage", "")

            //проверка даты
            val moscowTimeZone = TimeZone.getTimeZone("Europe/Moscow")
            val calendar = Calendar.getInstance(moscowTimeZone)
            val currentDate = calendar.get(Calendar.DAY_OF_YEAR)
            val lastUpdated = prefs.getInt("lastUpdated", 0)

            //сохранение инфы о упражнении
            if (existingResult.isNullOrEmpty() || currentDate > lastUpdated) {
                prefs.edit().putString("trainingStorage", result).apply()
                prefs.edit().putInt("lastUpdated", currentDate).apply()
                prefs.edit().putString("caloriesBurned", kkal.toString())?.apply()
            } else {
                val updatedResult = "$existingResult;$result"
                prefs.edit().putString("trainingStorage", updatedResult).apply()
                val caloriesBurned = prefs.getString("caloriesBurned", "0")
                prefs.edit().putString("caloriesBurned", (kkal + caloriesBurned!!.toInt()).toString())?.apply()
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
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddWorkoutFragment())
                .commit()
        }

    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_exercise, fragment)
        transaction.commit()
    }
}