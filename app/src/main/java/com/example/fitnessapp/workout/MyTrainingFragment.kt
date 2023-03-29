package com.example.fitnessapp.workout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.adapter.traningAdapter
import com.example.fitnessapp.databinding.FragmentMyTrainingBinding
import com.example.fitnessapp.model.traningModel
import java.util.*
import kotlin.collections.ArrayList

class MyTrainingFragment : Fragment() {

    private lateinit var adapter: traningAdapter
    private lateinit var recyclerView: RecyclerView
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

        if (existingResult.isNullOrEmpty() || currentDate > lastUpdated) {
            binding.textViewNotTraning.text = "Вы не выполнили ни одного упражнения сегодня"
        } else {
            initial()
        }

    }

    private fun initial() {
        recyclerView = binding.rvTraning
        adapter = traningAdapter()
        recyclerView.adapter = adapter
        adapter.setList(myTraning())
    }

    private fun myTraning(): ArrayList<traningModel> {
        val items = existingResult.split(";")
        val traningList = ArrayList<traningModel>()

        items.forEach { item ->
            val values = item.split("^")
            val name = values[0]
            val koll = values[1]
            val kkal = values[2]

            val trainingModel = traningModel(name, koll, kkal)
            traningList.add(trainingModel)
        }

        return traningList
    }
}