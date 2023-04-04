package com.example.fitnessapp

import android.app.Dialog
import android.content.Context
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

class ProfileFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentProfileBinding
    lateinit var bindingDialog: DialogWaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateProgressBar()
        //присваиваем актуальную дату
        updateDateText()

        bindingDialog = DialogWaterBinding.inflate(layoutInflater)
        val context = requireContext()
        val dialog = Dialog(context)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bindingDialog.root)


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
            dialog.show()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Ваш код обработки нажатия на кнопку назад
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    private fun updateProgressBar() {
        binding.progressBar.progress = 50
    }

    private fun updateDateText() {
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        prefs.edit().putString("saveDateToday", "26").apply()
        val dateFormat = SimpleDateFormat("dd MMMM", Locale("ru"))
        val currentDate = Date()
        val formattedDate = dateFormat.format(currentDate)
        binding.setDataText.text = formattedDate
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.water_glass_0 -> {
                bindingDialog.waterGlass0.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_1 -> {
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_2 -> {
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_3 -> {
                bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_4 -> {
                bindingDialog.waterGlass4.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_5 -> {
                bindingDialog.waterGlass5.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_6 -> {
                bindingDialog.waterGlass6.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_7 -> {
                bindingDialog.waterGlass7.setBackgroundResource(R.drawable.icon_bottle_water)
            }
            R.id.water_bottle_0 -> {
                bindingDialog.waterBottle0.setBackgroundResource(R.drawable.icon_bottle_water)
            }
            R.id.water_bottle_1 -> {
                bindingDialog.waterBottle1.setBackgroundResource(R.drawable.icon_bottle_water)
            }
            R.id.water_bottle_2 -> {
                bindingDialog.waterBottle2.setBackgroundResource(R.drawable.icon_bottle_water)
            }
            R.id.water_bottle_3 -> {
                bindingDialog.waterBottle3.setBackgroundResource(R.drawable.icon_bottle_water)
            }
            R.id.water_bottle_4 -> {
                bindingDialog.waterBottle4.setBackgroundResource(R.drawable.icon_bottle_water)
            }
            R.id.water_bottle_5 -> {
                bindingDialog.waterBottle5.setBackgroundResource(R.drawable.icon_bottle_water)
            }
            R.id.water_bottle_6 -> {
                bindingDialog.waterBottle6.setBackgroundResource(R.drawable.icon_bottle_water)
            }
            R.id.water_bottle_7 -> {
                bindingDialog.waterBottle7.setBackgroundResource(R.drawable.icon_bottle_water)
            }
        }
    }
}