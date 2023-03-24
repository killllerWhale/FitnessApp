package com.example.fitnessapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.fitnessapp.databinding.FragmentFoodBinding
import com.example.fitnessapp.databinding.FragmentTrainingBinding
import java.util.*

class FoodFragment : Fragment() {

    lateinit var binding: FragmentFoodBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFragment(FoodOneFragment())
        loadDataEat()
        binding.productSearch.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.staticMagnifier.visibility = View.GONE
            } else {
                if (binding.productSearch.text.toString().isEmpty()) {
                    binding.staticMagnifier.visibility = View.VISIBLE
                }
            }
            loadFragment(FoodTwoFragment())
        }

        binding.buttonCancel.setOnClickListener {
            binding.productSearch.text = null
            binding.productSearch.clearFocus()
            val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.productSearch.windowToken, 0)
            loadFragment(FoodOneFragment())

        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_food, fragment)
        transaction.commit()
    }

    private fun loadDataEat(){
        val currentTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"))
        val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
        System.out.println(currentHour)

        val textView = binding.textFoodDataNow

        when (currentHour) {
            in 6..10 -> textView.text = "Завтрак"
            in 11..16 -> textView.text = "Обед"
            in 17..23, in 0..5 -> textView.text = "Ужин"
        }

    }
}