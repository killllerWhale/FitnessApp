package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.fitnessapp.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

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
        val goSettings = binding.goSettings
        goSettings.setOnClickListener {
            val newFragment = SettingsFragment()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun updateProgressBar() {
        val progressBar: ProgressBar = binding.progressBar
        var textView: TextView = binding.textViewProgress
        progressBar.progress = 80
    }

    private fun updateDateText(){
        val dateFormat = SimpleDateFormat("dd MMMM", Locale("ru"))
        val currentDate = Date()
        val formattedDate = dateFormat.format(currentDate)
        var textView: TextView = binding.setDataText
        textView.text = formattedDate
    }

}