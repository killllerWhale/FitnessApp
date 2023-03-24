package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding.start0.setOnClickListener {
            loadFragment(Start0Fragment())
        }
        binding.start1.setOnClickListener {
            loadFragment(Start1Fragment())
        }
        binding.start2.setOnClickListener {
            loadFragment(Start2Fragment())
        }
        binding.goBack.setOnClickListener {
            loadFragment(Start2Fragment())
        }
        binding.next.setOnClickListener {
            if (binding.editTextWeight.text.isNotEmpty() && binding.editTextHeight.text.isNotEmpty()) {
                loadFragment(Start4Fragment())
            } else {
                val toast = Toast.makeText(requireActivity(), "Заполните поля", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}