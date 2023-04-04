package com.example.fitnessapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.FrafmentSettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingsFragment : Fragment() {

    lateinit var binding: FrafmentSettingBinding
    lateinit var prefs: SharedPreferences
    var mAuth: FirebaseAuth? = null
    var currentUser: FirebaseUser? = null
    var myDataBase: DatabaseReference? = null
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

    }

    private fun chengeDataBase() {
        mAuth!!.signInWithEmailAndPassword(prefs.getString("email_user", "0")!!, prefs.getString("password_user", "0")!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                currentUser = mAuth!!.currentUser
                val user_id = currentUser!!.uid
                myDataBase!!.child(user_id).child("progress")
                    .setValue(prefs.getString("progress", "0"))

            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}