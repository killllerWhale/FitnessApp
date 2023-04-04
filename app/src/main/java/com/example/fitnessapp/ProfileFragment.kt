package com.example.fitnessapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.example.fitnessapp.databinding.DialogWaterBinding
import com.example.fitnessapp.databinding.FragmentProfileBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class ProfileFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentProfileBinding
    lateinit var bindingDialog: DialogWaterBinding

    lateinit var prefs: SharedPreferences
    private lateinit var boolArrayGlass: BooleanArray
    private lateinit var boolArrayBottle: BooleanArray
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        boolArrayGlass = BooleanArray(8)
        boolArrayBottle = BooleanArray(8)


        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        val water = prefs.getString("water", "0;0")

        //присваиваем актуальную дату
        updateDateText()

        bindingDialog = DialogWaterBinding.inflate(layoutInflater)
        val context = requireContext()
        dialog = Dialog(context)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bindingDialog.root)
        if (water != null) {
            drawingWater(water)
        }

        binding.goSettings.setOnClickListener {
            val newFragment = SettingsFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.buttonGoWater.setOnClickListener {
            bindingDialog.waterGlass0.setOnClickListener(this)
            bindingDialog.waterGlass1.setOnClickListener(this)
            bindingDialog.waterGlass2.setOnClickListener(this)
            bindingDialog.waterGlass3.setOnClickListener(this)
            bindingDialog.waterGlass4.setOnClickListener(this)
            bindingDialog.waterGlass5.setOnClickListener(this)
            bindingDialog.waterGlass6.setOnClickListener(this)
            bindingDialog.waterGlass7.setOnClickListener(this)
            bindingDialog.waterBottle0.setOnClickListener(this)
            bindingDialog.waterBottle1.setOnClickListener(this)
            bindingDialog.waterBottle2.setOnClickListener(this)
            bindingDialog.waterBottle3.setOnClickListener(this)
            bindingDialog.waterBottle4.setOnClickListener(this)
            bindingDialog.waterBottle5.setOnClickListener(this)
            bindingDialog.waterBottle6.setOnClickListener(this)
            bindingDialog.waterBottle7.setOnClickListener(this)
            bindingDialog.addWaterButton.setOnClickListener(this)
            dialog.show()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Ваш код обработки нажатия на кнопку назад
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)


        val maxProgress = prefs.getString("max_progress", "1000;300;500;60")
        val maximumProgress = maxProgress!!.split(";")
        binding.maxCarbs.text = maximumProgress[1]
        binding.maxProtein.text = maximumProgress[2]
        binding.maxFats.text = maximumProgress[3]
        binding.textViewProgress.text = maximumProgress[0]
        binding.progressBar.max = maximumProgress[0].toInt()
        val foodProgress = prefs.getString("food_info", "0;0;0;0")
        val food = foodProgress!!.split(";")
        updateProgressBar(food[0])
        updateProgressBarFats(food[3])
        updateProgressBarProtein(food[2])
        updateProgressBarCarbohydrates(food[1])
    }

    private fun updateProgressBar(s: String) {
        binding.setKkal.text = s
        if ((binding.textViewProgress.text.toString().toInt() - s.toInt()) < 0){
            binding.textViewProgress.text = "0"
        }
        else{
            binding.textViewProgress.text = (binding.textViewProgress.text.toString().toInt() - s.toInt()).toString()
        }
        binding.progressBar.progress = s.toInt()
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgressBarFats(s: String) {
        binding.setFats.text = "$s /"
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgressBarProtein(s: String) {
        binding.setProtein.text = "$s /"
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgressBarCarbohydrates(s: String) {
        binding.setCarbohydrates.text = "$s /"
    }


    private fun updateDateText() {
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        prefs.edit().putString("saveDateToday", "26").apply()
        val dateFormat = SimpleDateFormat("dd MMMM", Locale("ru"))
        val currentDate = Date()
        val formattedDate = dateFormat.format(currentDate)
        binding.setDataText.text = formattedDate
    }

    private fun drawingWater(water:String){
        val array = water.split(";")
        when (array[0].toInt()){
            1 ->{
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                boolArrayGlass[0] = true
            }
            2 ->{
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
                boolArrayGlass[0] = true
                boolArrayGlass[1] = true
            }
            3 ->{
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
                boolArrayGlass[0] = true
                boolArrayGlass[1] = true
                boolArrayGlass[2] = true
            }
            4 ->{
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_water)
                boolArrayGlass[0] = true
                boolArrayGlass[1] = true
                boolArrayGlass[2] = true
                boolArrayGlass[3] = true
            }
            5 ->{
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass4.setBackgroundResource(R.drawable.icon_water)
                boolArrayGlass[0] = true
                boolArrayGlass[1] = true
                boolArrayGlass[2] = true
                boolArrayGlass[3] = true
                boolArrayGlass[4] = true
            }
            6 ->{
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass4.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass5.setBackgroundResource(R.drawable.icon_water)
                boolArrayGlass[0] = true
                boolArrayGlass[1] = true
                boolArrayGlass[2] = true
                boolArrayGlass[3] = true
                boolArrayGlass[4] = true
                boolArrayGlass[5] = true
            }
            7 ->{
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass4.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass5.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass6.setBackgroundResource(R.drawable.icon_water)
                boolArrayGlass[0] = true
                boolArrayGlass[1] = true
                boolArrayGlass[2] = true
                boolArrayGlass[3] = true
                boolArrayGlass[4] = true
                boolArrayGlass[5] = true
                boolArrayGlass[6] = true
            }
            8 ->{
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass4.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass5.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass6.setBackgroundResource(R.drawable.icon_water)
                bindingDialog.waterGlass7.setBackgroundResource(R.drawable.icon_water)
                boolArrayGlass[0] = true
                boolArrayGlass[1] = true
                boolArrayGlass[2] = true
                boolArrayGlass[3] = true
                boolArrayGlass[4] = true
                boolArrayGlass[5] = true
                boolArrayGlass[6] = true
                boolArrayGlass[7] = true
            }
        }
        when (array[1].toInt()){
            1 ->{
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                boolArrayBottle[0] = true
            }
            2 ->{
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
                boolArrayBottle[0] = true
                boolArrayBottle[1] = true
            }
            3 ->{
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water)
                boolArrayBottle[0] = true
                boolArrayBottle[1] = true
                boolArrayBottle[2] = true
            }
            4 ->{
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle3.setBackgroundResource(R.drawable.icon_bottle_water)
                boolArrayBottle[0] = true
                boolArrayBottle[1] = true
                boolArrayBottle[2] = true
                boolArrayBottle[3] = true
            }
            5 ->{
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle3.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle4.setBackgroundResource(R.drawable.icon_bottle_water)
                boolArrayBottle[0] = true
                boolArrayBottle[1] = true
                boolArrayBottle[2] = true
                boolArrayBottle[3] = true
                boolArrayBottle[4] = true
            }
            6 ->{
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle3.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle4.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle5.setBackgroundResource(R.drawable.icon_bottle_water)
                boolArrayBottle[0] = true
                boolArrayBottle[1] = true
                boolArrayBottle[2] = true
                boolArrayBottle[3] = true
                boolArrayBottle[4] = true
                boolArrayBottle[5] = true
            }
            7 ->{
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle3.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle4.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle5.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle6.setBackgroundResource(R.drawable.icon_bottle_water)
                boolArrayBottle[0] = true
                boolArrayBottle[1] = true
                boolArrayBottle[2] = true
                boolArrayBottle[3] = true
                boolArrayBottle[4] = true
                boolArrayBottle[5] = true
                boolArrayBottle[6] = true
            }
            8 ->{
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle3.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle4.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle5.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle6.setBackgroundResource(R.drawable.icon_bottle_water)
                bindingDialog.waterBottle7.setBackgroundResource(R.drawable.icon_bottle_water)
                boolArrayBottle[0] = true
                boolArrayBottle[1] = true
                boolArrayBottle[2] = true
                boolArrayBottle[3] = true
                boolArrayBottle[4] = true
                boolArrayBottle[5] = true
                boolArrayBottle[6] = true
                boolArrayBottle[7] = true
            }
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.water_glass_0 -> {
                val value = if (!boolArrayGlass[0]){
                    bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
                    true
                }else{
                    bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_glass_water_null)
                    false
                }
                boolArrayGlass[0] = value
            }
            R.id.water_glass_1 -> {
                val value = if (!boolArrayGlass[1]){
                    bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
                    true
                }else{
                    bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_glass_water_null)
                    false
                }
                boolArrayGlass[1] = value
            }
            R.id.water_glass_2 -> {
                val value = if (!boolArrayGlass[2]){
                    bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
                    true
                }else{
                    bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_glass_water_null)
                    false
                }
                boolArrayGlass[2] = value
            }
            R.id.water_glass_3 -> {
                val value = if (!boolArrayGlass[3]){
                    bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_water)
                    true
                }else{
                    bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_glass_water_null)
                    false
                }
                boolArrayGlass[3] = value
            }
            R.id.water_glass_4 -> {
                val value = if (!boolArrayGlass[4]){
                    bindingDialog.waterGlass4.setBackgroundResource(R.drawable.icon_water)
                    true
                }else{
                    bindingDialog.waterGlass4.setBackgroundResource(R.drawable.icon_glass_water_null)
                    false
                }
                boolArrayGlass[4] = value
            }
            R.id.water_glass_5 -> {
                val value = if (!boolArrayGlass[5]){
                    bindingDialog.waterGlass5.setBackgroundResource(R.drawable.icon_water)
                    true
                }else{
                    bindingDialog.waterGlass5.setBackgroundResource(R.drawable.icon_glass_water_null)
                    false
                }
                boolArrayGlass[5] = value
            }
            R.id.water_glass_6 -> {
                val value = if (!boolArrayGlass[6]){
                    bindingDialog.waterGlass6.setBackgroundResource(R.drawable.icon_water)
                    true
                }else{
                    bindingDialog.waterGlass6.setBackgroundResource(R.drawable.icon_glass_water_null)
                    false
                }
                boolArrayGlass[6] = value
            }
            R.id.water_glass_7 -> {
                val value = if (!boolArrayGlass[7]){
                    bindingDialog.waterGlass7.setBackgroundResource(R.drawable.icon_water)
                    true
                }else{
                    bindingDialog.waterGlass7.setBackgroundResource(R.drawable.icon_glass_water_null)
                    false
                }
                boolArrayGlass[7] = value
            }
            R.id.water_bottle_0 -> {
                val value = if (!boolArrayBottle[0]){
                    bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
                    true
                }else{
                    bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water_null)
                    false
                }
                boolArrayBottle[0] = value
            }
            R.id.water_bottle_1 -> {
                val value = if (!boolArrayBottle[1]){
                    bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
                    true
                }else{
                    bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water_null)
                    false
                }
                boolArrayBottle[1] = value
            }
            R.id.water_bottle_2 -> {
                val value = if (!boolArrayBottle[2]){
                    bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water)
                    true
                }else{
                    bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water_null)
                    false
                }
                boolArrayBottle[2] = value
            }
            R.id.water_bottle_3 -> {
                val value = if (!boolArrayBottle[3]){
                    bindingDialog.waterBottle3.setBackgroundResource(R.drawable.icon_bottle_water)
                    true
                }else{
                    bindingDialog.waterBottle3.setBackgroundResource(R.drawable.icon_bottle_water_null)
                    false
                }
                boolArrayBottle[3] = value
            }
            R.id.water_bottle_4 -> {
                val value = if (!boolArrayBottle[4]){
                    bindingDialog.waterBottle4.setBackgroundResource(R.drawable.icon_bottle_water)
                    true
                }else{
                    bindingDialog.waterBottle4.setBackgroundResource(R.drawable.icon_bottle_water_null)
                    false
                }
                boolArrayBottle[4] = value
            }
            R.id.water_bottle_5 -> {
                val value = if (!boolArrayBottle[5]){
                    bindingDialog.waterBottle5.setBackgroundResource(R.drawable.icon_bottle_water)
                    true
                }else{
                    bindingDialog.waterBottle5.setBackgroundResource(R.drawable.icon_bottle_water_null)
                    false
                }
                boolArrayBottle[5] = value
            }
            R.id.water_bottle_6 -> {
                val value = if (!boolArrayBottle[6]){
                    bindingDialog.waterBottle6.setBackgroundResource(R.drawable.icon_bottle_water)
                    true
                }else{
                    bindingDialog.waterBottle6.setBackgroundResource(R.drawable.icon_bottle_water_null)
                    false
                }
                boolArrayBottle[6] = value
            }
            R.id.water_bottle_7 -> {
                val value = if (!boolArrayBottle[7]){
                    bindingDialog.waterBottle7.setBackgroundResource(R.drawable.icon_bottle_water)
                    true
                }else{
                    bindingDialog.waterBottle7.setBackgroundResource(R.drawable.icon_bottle_water_null)
                    false
                }
                boolArrayBottle[7] = value
            }
            R.id.add_water_button ->{
                var glassOk = 0
                for (i in boolArrayGlass){
                    if (i){
                        glassOk += 1
                    }
                }
                var bottleOk = 0
                for (i in boolArrayBottle){
                    if (i){
                        bottleOk += 1
                    }
                }
                prefs.edit().putString("water", "$glassOk;$bottleOk").apply()
                dialog.hide()
            }
        }
    }
}