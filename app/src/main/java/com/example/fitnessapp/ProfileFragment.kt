package com.example.fitnessapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
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

class ProfileFragment : Fragment() {

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

        binding.goSettings.setOnClickListener {
            val newFragment = SettingsFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.buttonGoWater.setOnClickListener {
            val context = requireContext()
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_water)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            bindingDialog = DialogWaterBinding.inflate(layoutInflater)
            bindingDialog.waterGlass2.setOnClickListener {
                bindingDialog.waterGlass2.setBackgroundResource(R.drawable.icon_water)
            }
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

}