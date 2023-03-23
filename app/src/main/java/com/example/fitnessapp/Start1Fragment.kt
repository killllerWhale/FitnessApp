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
        val context = requireContext()
        binding.male.setBackgroundColor(ContextCompat.getColor(context, R.color.color_background_gray))
        binding.female.setBackgroundColor(ContextCompat.getColor(context, R.color.color_background_gray))
        when(view?.id){
            R.id.female->{
                binding.female.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                binding.next.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                choose = 1
            }
            R.id.male->{
                binding.male.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                binding.next.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
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