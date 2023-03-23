package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.fitnessapp.databinding.FragmentStart0Binding


class Start0Fragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentStart0Binding
    var choose = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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

        val context = requireContext()
        binding.beFit.setBackgroundColor(ContextCompat.getColor(context, R.color.color_background_gray))
        binding.loseWeight.setBackgroundColor(ContextCompat.getColor(context, R.color.color_background_gray))
        binding.musclesUp.setBackgroundColor(ContextCompat.getColor(context, R.color.color_background_gray))
        when(view?.id){
            R.id.be_fit->{
                binding.beFit.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                binding.next.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                choose = 1
            }
            R.id.muscles_up->{
                binding.musclesUp.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                binding.next.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                choose = 1
            }
            R.id.lose_weight->{
                binding.loseWeight.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                binding.next.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
                choose = 1
            }
            R.id.go_back->{
                loadFragment(EntryFragment())
            }
            R.id.next->{
                if (choose == 1){
                    loadFragment(Start1Fragment())
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