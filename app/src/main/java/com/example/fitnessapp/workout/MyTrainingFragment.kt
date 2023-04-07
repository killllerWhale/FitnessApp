package com.example.fitnessapp.workout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.adapter.TraningAdapter
import com.example.fitnessapp.databinding.FragmentMyTrainingBinding
import com.example.fitnessapp.model.TraningModel
import java.util.*
import kotlin.collections.ArrayList

class MyTrainingFragment : Fragment() {

    private lateinit var adapter: TraningAdapter
    private lateinit var binding: FragmentMyTrainingBinding
    private lateinit var existingResult: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyTrainingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        existingResult = prefs!!.getString("trainingStorage", "").toString()

        val moscowTimeZone = TimeZone.getTimeZone("Europe/Moscow")
        val calendar = Calendar.getInstance(moscowTimeZone)
        val currentDate = calendar.get(Calendar.DAY_OF_YEAR)
        val lastUpdated = prefs.getInt("lastUpdated", 0)

        if (existingResult.isEmpty() || currentDate > lastUpdated) {
            binding.textViewNotTraning.text = "Вы не выполнили ни одного упражнения сегодня"
        } else {
            initial()
        }

    }

    private fun initial() {
        adapter = TraningAdapter()
        binding.rvTraning.adapter = adapter
        adapter.setList(myTraning())
    }

    private fun myTraning(): ArrayList<TraningModel> {
        val items = existingResult.split(";")
        val traningList = ArrayList<TraningModel>()

        items.forEach { item ->
            val values = item.split("^")
            val (name, koll, kkal) = values


            val trainingModel = TraningModel(name, koll, kkal)
            traningList.add(trainingModel)
        }

        return traningList
    }
}