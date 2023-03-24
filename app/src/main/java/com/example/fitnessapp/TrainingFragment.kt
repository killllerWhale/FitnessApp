package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FragmentTrainingBinding
import java.text.SimpleDateFormat
import java.util.*

class TrainingFragment : Fragment() {

    lateinit var binding: FragmentTrainingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTrainingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //присваиваем актуальную дату
        updateDateText()
        updateProgressBar()
    }

    private fun updateProgressBar() {
        val progressBar: ProgressBar = binding.progressBar
        progressBar.progress = 10
    }

    private fun updateDateText() {
        val dateFormat = SimpleDateFormat("dd MMMM", Locale("ru"))
        val currentDate = Date()
        val formattedDate = dateFormat.format(currentDate)
        var textView: TextView = binding.setDataText
        textView.text = formattedDate
    }

}