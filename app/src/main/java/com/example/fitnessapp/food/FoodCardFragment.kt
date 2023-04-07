package com.example.fitnessapp.food

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.DialogFoodGramBinding
import com.example.fitnessapp.databinding.FragmentFoodCardBinding
import com.example.fitnessapp.pars.food.FoodCardsList
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.RoundingMode
import java.util.*

class FoodCardFragment : Fragment() {

    private lateinit var binding: FragmentFoodCardBinding

    private lateinit var bindingDialog: DialogFoodGramBinding

    private lateinit var result: String

    private lateinit var prefs: SharedPreferences

    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodCardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)!!
        var gram = prefs.getInt("gram", 100)


        bindingDialog = DialogFoodGramBinding.inflate(layoutInflater)
        val context = requireContext()
        val dialog = Dialog(context)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bindingDialog.root)

        bindingDialog.addFoodGram.setOnClickListener {
            if (bindingDialog.editText.text.toString() != "" && bindingDialog.editText.text.toString()
                    .isDigitsOnly()
            ) {
                prefs.edit().putInt("gram", bindingDialog.editText.text.toString().toInt()).apply()
                gram = prefs.getInt("gram", 100)
                binding.textPortion.text = "Порция $gram грамм"
            } else {
                binding.textPortion.text = "Порция $gram грамм"
            }
            bindingDialog.editText.setText("")
            dialog.dismiss()
            updateFoodInfo()
        }

        binding.setPortion.setOnClickListener {
            dialog.show()
        }

        binding.addFood.setOnClickListener {
            val foodProgress = prefs.getString("food_info", "0;0;0;0")
            val food = foodProgress!!.split(";")
            val info = (food[0].toDouble().toInt() + binding.containsKkal.text.toString().toDouble()
                .toInt()).toString() + ";" + (food[1].toDouble()
                .toInt() + binding.containsArbohydrates.text.toString().toDouble()
                .toInt()).toString() + ";" + (food[2].toDouble()
                .toInt() + binding.containsProtein.text.toString().toDouble()
                .toInt()).toString() + ";" + (food[3].toDouble()
                .toInt() + binding.containsFats.text.toString().toDouble().toInt()).toString()
            result =
                binding.foodName.text.toString() + "^" + binding.textPortion.text + "^потреблено: " + binding.containsKkal.text + " ккал"
            val existingResult = prefs.getString("foodStorage", "")
            prefs.getString("saveDateToday", "")

            //проверка даты
            val moscowTimeZone = TimeZone.getTimeZone("Europe/Moscow")
            val calendar = Calendar.getInstance(moscowTimeZone)
            val currentDate = calendar.get(Calendar.DAY_OF_YEAR)
            val lastUpdated = prefs.getInt("lastUpdated", 0)

            //сохранение инфы о упражнении
            if (existingResult.isNullOrEmpty() || currentDate > lastUpdated) {
                prefs.edit()?.putString("foodStorage", result)?.apply()
                prefs.edit().putInt("lastUpdated", currentDate).apply()
            } else {
                val updatedResult = "$existingResult;$result"
                prefs.edit()?.putString("foodStorage", updatedResult)?.apply()
            }
            prefs.edit().putInt("gram", 100).apply()
            prefs.edit().putString("food_position", "").apply()
            prefs.edit().putString("food_info", info).apply()
            loadFragment(FoodOneFragment())
        }

        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.product_contents)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, FoodCardsList::class.java)
        for (i in 0..66) {
            if (prefs.getString("food_position", "") == post.food[i].name) {
                position = i
                Log.d(TAG, position.toString())
            }
        }
        binding.foodName.text = post.food[position].name
        updateFoodInfo()

    }

    private fun updateFoodInfo() {
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        val gram = prefs!!.getInt("gram", 100)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.product_contents)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, FoodCardsList::class.java)
        with(binding) {
            containsKkal.text = ((post.food[position].kkal / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsCalcium.text = ((post.food[position].calcium / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsAsh.text = ((post.food[position].ash / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsFats.text = ((post.food[position].fats / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsCholesterol.text =
                ((post.food[position].cholesterol / 10.0) * gram).toBigDecimal()
                    .setScale(2, RoundingMode.UP).toDouble().toString()
            containsFiber.text = ((post.food[position].fiber / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsMagnesium.text = ((post.food[position].magnesium / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsProtein.text = ((post.food[position].proteins / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsArbohydrates.text =
                ((post.food[position].carbohydrates / 10.0) * gram).toBigDecimal()
                    .setScale(2, RoundingMode.UP).toDouble().toString()
            containsSakharov.text = ((post.food[position].sakharov / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsWater.text = ((post.food[position].water / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
            containsIron.text = ((post.food[position].iron / 10.0) * gram).toBigDecimal()
                .setScale(2, RoundingMode.UP).toDouble().toString()
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_food, fragment)
        transaction.commit()
    }
}