package com.example.fitnessapp.start

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.DialogEnterBinding
import com.example.fitnessapp.databinding.FragmentEntryBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EntryFragment : Fragment() {

    private lateinit var binding: FragmentEntryBinding
    lateinit var bindingDialog: DialogEnterBinding
    var mAuth: FirebaseAuth? = null
    var currentUser: FirebaseUser? = null
    var myDataBase: DatabaseReference? = null
    var USER_KEY = "User"

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

        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY)
        mAuth = FirebaseAuth.getInstance()

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
                if (TextUtils.isEmpty(bindingDialog.editTextEmail.text.toString()) && TextUtils.isEmpty(bindingDialog.editTextPassword.text.toString())) {
                    System.out.println("[[[")
                } else {
                    mAuth!!.signInWithEmailAndPassword(
                        bindingDialog.editTextEmail.text.toString(),
                        bindingDialog.editTextPassword.text.toString()
                    ).addOnCompleteListener(
                        OnCompleteListener { task ->
                            if (task.isSuccessful) {
                                System.out.println("[[")
                                startActivity(Intent(activity, MainActivity::class.java))
                            } else {
                                System.out.println("[")
                            }
                        })
                }
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