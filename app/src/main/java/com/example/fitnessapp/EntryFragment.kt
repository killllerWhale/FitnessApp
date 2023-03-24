package com.example.fitnessapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.fitnessapp.databinding.DialogEnterBinding
import com.example.fitnessapp.databinding.DialogWaterBinding
import com.example.fitnessapp.databinding.FragmentEntryBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class EntryFragment : Fragment() {

    private lateinit var binding: FragmentEntryBinding
    lateinit var bindingDialog: DialogEnterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEntryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingDialog = DialogEnterBinding.inflate(layoutInflater)
        val context = requireContext()
        val dialog = BottomSheetDialog(context)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(bindingDialog.root)

        binding.go.setOnClickListener {
            loadFragment(Start0Fragment())
        }
        binding.enter.setOnClickListener {
            bindingDialog.enter.setOnClickListener {
                startActivity(Intent(activity, MainActivity::class.java))
            }
            dialog.show()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}