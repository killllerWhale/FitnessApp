package com.example.fitnessapp.profile

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.activity.EntryActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.DialogChangeWeightBinding
import com.example.fitnessapp.databinding.FrafmentSettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingsFragment : Fragment() {

    lateinit var binding: FrafmentSettingBinding
    private val bindingDialog: DialogChangeWeightBinding by lazy {
        DialogChangeWeightBinding.inflate(layoutInflater)
    }
    lateinit var prefs: SharedPreferences
    var mAuth: FirebaseAuth? = null
    var currentUser: FirebaseUser? = null
    var myDataBase: DatabaseReference? = null
    lateinit var dialog: Dialog
    var USER_KEY = "User"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FrafmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)

        dialog = Dialog(requireContext()).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(bindingDialog.root)
        }

        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY)
        mAuth = FirebaseAuth.getInstance()

        binding.setNameText.text = prefs.getString("user_name", "0")!!
        prefs.getInt("user_target", 0)
        binding.setBirthText.text = prefs.getString("user_birthday", "0")
        if (prefs.getInt("user_gender", 0) == 1) {
            binding.setSexText.text = "мужчина"
        }else{
            binding.setSexText.text = "женщина"
        }
        binding.setWeightText.text = prefs.getString("user_weight", "0")
        binding.setHeightText.text = prefs.getString("user_height", "0")

        binding.goBack.setOnClickListener {
            loadFragment(ProfileFragment())
        }

        binding.changeTarget.setOnClickListener {
            loadFragment(ProfileFragment())
        }

        binding.exit.setOnClickListener {
            prefs.edit().putInt("user", 0).apply()
            startActivity(Intent(activity, EntryActivity::class.java))
        }

        binding.setWeightText.setOnClickListener {
            dialog.show()
        }

        bindingDialog.cancellation.setOnClickListener {
            dialog.dismiss()
        }

        bindingDialog.enter.setOnClickListener {
            val weight = bindingDialog.editText3.text.toString()
            if (weight.isEmpty()) {
                Toast.makeText(requireContext(), "Пожалуйста, введите действительный вес", Toast.LENGTH_SHORT).show()
            } else {
                changeDataBase("weight", weight)
                binding.setWeightText.text = weight
                prefs.edit().putString("user_weight", weight).apply()
                dialog.dismiss()
            }
        }

    }

    private fun changeDataBase( text: String, textChange: String) {
        mAuth!!.signInWithEmailAndPassword(prefs.getString("email_user", "0")!!, prefs.getString("password_user", "0")!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                currentUser = mAuth!!.currentUser
                val user_id = currentUser!!.uid
                myDataBase!!.child(user_id).child(text)
                    .setValue(textChange)
                System.out.println("rrr")
            }else{
                System.out.println("r")
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}