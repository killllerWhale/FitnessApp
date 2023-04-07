package com.example.fitnessapp.start

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentStart3Binding

class Start3Fragment : Fragment() {

    private lateinit var binding: FragmentStart3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStart3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        binding.apply {
            start0.setOnClickListener { loadFragment(Start0Fragment()) }
            start1.setOnClickListener { loadFragment(Start1Fragment()) }
            start2.setOnClickListener { loadFragment(Start2Fragment()) }
            goBack.setOnClickListener { loadFragment(Start2Fragment()) }
            next.setOnClickListener {
                if (editTextWeight.text.isNotEmpty() && editTextHeight.text.isNotEmpty()) {
                    with(prefs.edit()) {
                        putString("user_weight", editTextWeight.text.toString())
                        putString("user_height", editTextHeight.text.toString())
                        apply()
                    }
                    loadFragment(Start4Fragment())
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