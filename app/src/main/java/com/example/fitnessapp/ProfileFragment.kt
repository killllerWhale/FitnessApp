package com.example.fitnessapp

import android.app.Dialog
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
import com.example.fitnessapp.databinding.DialogWaterBinding
import com.example.fitnessapp.databinding.FragmentProfileBinding
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentProfileBinding
    lateinit var bindingDialog : DialogWaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
            bindingDialog.waterGlass1.setOnClickListener(this)
            bindingDialog.waterGlass2.setOnClickListener(this)
            bindingDialog.waterGlass3.setOnClickListener(this)
            bindingDialog.waterGlass4.setOnClickListener(this)
            bindingDialog.waterGlass5.setOnClickListener(this)
            bindingDialog.waterGlass6.setOnClickListener(this)
            bindingDialog.waterGlass7.setOnClickListener(this)
            bindingDialog.waterGlass8.setOnClickListener(this)
            dialog.show()
        }
    }


    private fun updateStatusWater(){

    }

    private fun updateProgressBar() {
        val progressBar: ProgressBar = binding.progressBar
        var textView: TextView = binding.textViewProgress
        progressBar.progress = 50
    }

    private fun updateDateText(){
        val dateFormat = SimpleDateFormat("dd MMMM", Locale("ru"))
        val currentDate = Date()
        val formattedDate = dateFormat.format(currentDate)
        var textView: TextView = binding.setDataText
        textView.text = formattedDate
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.water_glass_1->{
                bindingDialog.waterGlass1.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_2->{
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_3->{
                bindingDialog.waterGlass3.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_4->{
                bindingDialog.waterGlass4.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_5->{
                bindingDialog.waterGlass5.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_6->{
                bindingDialog.waterGlass6.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_7->{
                bindingDialog.waterGlass6.setBackgroundResource(R.drawable.icon_water)
            }
            R.id.water_glass_8->{
                bindingDialog.waterGlass6.setBackgroundResource(R.drawable.icon_water)
            }
        }
    }
}