package com.example.fitnessapp.pars.start

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentStart4Binding

class Start4Fragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentStart4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStart4Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener(this)
        binding.goBack.setOnClickListener(this)
        binding.start1.setOnClickListener(this)
        binding.start2.setOnClickListener(this)
        binding.start3.setOnClickListener(this)
        binding.start4.setOnClickListener(this)
        binding.start0.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.start0 -> {
                loadFragment(Start0Fragment())
            }
            R.id.start1 -> {
                loadFragment(Start1Fragment())
            }
            R.id.start2 -> {
                loadFragment(Start2Fragment())
            }
            R.id.start3 -> {
                loadFragment(Start3Fragment())
            }
            R.id.go_back -> {
                loadFragment(Start3Fragment())
            }
            R.id.next -> {
                if (binding.editTextName.text.isNotEmpty() && binding.editTextEmail.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty() && binding.editTextPasswordRepeat.text.isNotEmpty() && (binding.editTextPassword.text.toString() == binding.editTextPasswordRepeat.text.toString())) {
                    startActivity(Intent(activity, MainActivity::class.java))
                } else {
                    if (binding.editTextName.text.isEmpty() || binding.editTextEmail.text.isEmpty() || binding.editTextPassword.text.isEmpty() || binding.editTextPasswordRepeat.text.isEmpty()) {
                        val toast =
                            Toast.makeText(requireActivity(), "Введите данные", Toast.LENGTH_SHORT)
                        toast.show()
                    } else {
                        val toast = Toast.makeText(
                            requireActivity(),
                            "Пароли не совпадают",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}