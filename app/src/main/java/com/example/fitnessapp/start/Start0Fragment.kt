package com.example.fitnessapp.start

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentStart0Binding


class Start0Fragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentStart0Binding
    var choose = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStart0Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBack.setOnClickListener(this)
        binding.beFit.setOnClickListener(this)
        binding.loseWeight.setOnClickListener(this)
        binding.musclesUp.setOnClickListener(this)
        binding.next.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        binding.beFit.setBackgroundResource(R.drawable.color_background_gray_corners)
        binding.loseWeight.setBackgroundResource(R.drawable.color_background_gray_corners)
        binding.musclesUp.setBackgroundResource(R.drawable.color_background_gray_corners)
        when (view?.id) {
            R.id.be_fit -> {
                prefs.edit().putInt("user_target", 1).apply()
                binding.beFit.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                binding.next.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                choose = 1
            }
            R.id.muscles_up -> {
                prefs.edit().putInt("user_target", 2).apply()
                binding.musclesUp.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                binding.next.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                choose = 1
            }
            R.id.lose_weight -> {
                prefs.edit().putInt("user_target", 0).apply()
                binding.loseWeight.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                binding.next.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                choose = 1
            }
            R.id.go_back -> {
                loadFragment(EntryFragment())
            }
            R.id.next -> {
                if (choose == 1) {
                    loadFragment(Start1Fragment())
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