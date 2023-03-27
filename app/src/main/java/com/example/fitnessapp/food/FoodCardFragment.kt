package com.example.fitnessapp.food

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
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
import com.example.fitnessapp.pars.food.Food
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class FoodCardFragment : Fragment() {

    private lateinit var binding: FragmentFoodCardBinding

    private lateinit var bindingDialog: DialogFoodGramBinding

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
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        var gram = prefs!!.getInt("gram", 100)


        bindingDialog = DialogFoodGramBinding.inflate(layoutInflater)
        val context = requireContext()
        val dialog = Dialog(context)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bindingDialog.root)

        bindingDialog.addFoodGram.setOnClickListener{
            if (bindingDialog.editText.text.toString() != "" && bindingDialog.editText.text.toString().isDigitsOnly()){
                prefs.edit().putInt("gram", bindingDialog.editText.text.toString().toInt()).apply()
                gram = prefs.getInt("gram", 100)
                binding.textPortion.text = "Порция ($gram)г"
            }else{
                binding.textPortion.text = "Порция ($gram)г"
            }
            bindingDialog.editText.setText("")
            dialog.hide()
            updateFoodInfo()
        }

        binding.setPortion.setOnClickListener{
            dialog.show()
        }

        val gson = Gson()
        val bufferedReader = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.product_contents)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, Food::class.java)
        for (i in 0..66){
            if (prefs.getString("food_position", "") == post.food[i].name){
                position = i
                Log.d(TAG, position.toString())
            }
        }
        binding.foodName.text = post.food[position].name
        updateFoodInfo()

    }
    fun updateFoodInfo(){
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        val gram = prefs!!.getInt("gram", 100)
        val gson = Gson()
        val bufferedReader =
            BufferedReader(InputStreamReader(resources.openRawResource(R.raw.product_contents)))
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, Food::class.java)
        binding.containsKkal.text = ((post.food[position].kkal / 10) * gram).toInt().toString()
        binding.containsCalcium.text = ((post.food[position].calcium / 10) * gram).toInt().toString()
        binding.containsAsh.text = ((post.food[position].ash / 10) * gram).toInt().toString()
        binding.containsFats.text = ((post.food[position].fats / 10) * gram).toInt().toString()
        binding.containsCholesterol.text = ((post.food[position].cholesterol / 10) * gram).toInt().toString()
        binding.containsFiber.text = ((post.food[position].fiber / 10) * gram).toInt().toString()
        binding.containsMagnesium.text = ((post.food[position].magnesium / 10) * gram).toInt().toString()
        binding.containsProtein.text = ((post.food[position].proteins / 10) * gram).toInt().toString()
        binding.containsArbohydrates.text = ((post.food[position].carbohydrates / 10) * gram).toInt().toString()
        binding.containsSakharov.text = ((post.food[position].sakharov / 10) * gram).toInt().toString()
        binding.containsWater.text = ((post.food[position].water / 10) * gram).toInt().toString()
        binding.containsIron.text = ((post.food[position].iron / 10) * gram).toInt().toString()
    }

}