package com.example.fitnessapp.start

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentStart2Binding

class Start2Fragment : Fragment() {

    private lateinit var binding: FragmentStart2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStart2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        binding.apply {
            goBack.setOnClickListener { loadFragment(Start1Fragment()) }
            start0.setOnClickListener { loadFragment(Start0Fragment()) }
            start1.setOnClickListener { loadFragment(Start1Fragment()) }
            next.setOnClickListener {
                val day = editTextDay.text.toString()
                val month = editTextMonth.text.toString()
                val year = editTextYear.text.toString()
                if (day.isNotEmpty() && month.isNotEmpty() && year.isNotEmpty()) {
                    val result = "$day.$month.$year"
                    prefs.edit().putString("user_birthday", result).apply()
                    loadFragment(Start3Fragment())
                } else {
                    toast("Заполните поля")
                }
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, duration).show()
}