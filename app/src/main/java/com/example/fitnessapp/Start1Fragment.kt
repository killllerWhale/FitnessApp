package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.fitnessapp.databinding.FragmentStart1Binding

class Start1Fragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentStart1Binding
    var choose = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        binding.male.setBackgroundResource(R.drawable.color_background_gray_corners)
        binding.female.setBackgroundResource(R.drawable.color_background_gray_corners)
        when(view?.id){
            R.id.female->{
                binding.female.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                binding.next.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                choose = 1
            }
            R.id.male->{
                binding.male.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                binding.next.setBackgroundResource(R.drawable.color_background_teal_700_corners)
                choose = 1
            }
            R.id.go_back->{
                loadFragment(Start0Fragment())
            }
            R.id.start0->{
                loadFragment(Start0Fragment())
            }
            R.id.next->{
                if (choose == 1){
                    loadFragment(Start2Fragment())
                }
                else{
                    val toast = Toast.makeText(requireActivity(), "Сделайте выбор", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}