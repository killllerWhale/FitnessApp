package com.example.fitnessapp.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentStart1Binding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class Start1Fragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentStart1Binding
    var choose = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStart1Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goBack.setOnClickListener(this)
        binding.male.setOnClickListener(this)
        binding.female.setOnClickListener(this)
        binding.next.setOnClickListener(this)
        binding.start0.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        binding.male.setBackgroundResource(R.drawable.color_background_gray_corners)
        binding.female.setBackgroundResource(R.drawable.color_background_gray_corners)

        when (view?.id) {
            R.id.female -> {
                prefs.edit().putInt("user_gender", 0).apply()
                binding.female.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                binding.next.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                choose = 1
            }
            R.id.male -> {
                prefs.edit().putInt("user_gender", 1).apply()
                binding.male.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                binding.next.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                choose = 1
            }
            R.id.go_back, R.id.start0 -> {
                loadFragment(Start0Fragment())
            }
            R.id.next -> {
                if (choose == 1) {
                    loadFragment(Start2Fragment())
                } else {
                    toast("Сделайте выбор")
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