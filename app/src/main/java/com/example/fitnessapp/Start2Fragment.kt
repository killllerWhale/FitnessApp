package com.example.fitnessapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        binding.goBack.setOnClickListener {
            loadFragment(Start1Fragment())
        }
        binding.start0.setOnClickListener {
            loadFragment(Start0Fragment())
        }
        binding.start1.setOnClickListener {
            loadFragment(Start1Fragment())
        }
        binding.next.setOnClickListener {
            if (binding.editTextDay.text.isNotEmpty() && binding.editTextMonth.text.isNotEmpty() && binding.editTextYear.text.isNotEmpty()) {
                loadFragment(Start3Fragment())
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