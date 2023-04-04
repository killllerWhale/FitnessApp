package com.example.fitnessapp.start

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
    var myDataBase: DatabaseReference? = null
    var USER_KEY = "User"
    var currentUser: FirebaseUser? = null
    lateinit var prefs: SharedPreferences

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

        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)

        binding.go.setOnClickListener {
            loadFragment(Start0Fragment())
        }
        binding.enter.setOnClickListener {
            bindingDialog.enter.setOnClickListener {
                //вход
                if (TextUtils.isEmpty(bindingDialog.editTextEmail.text.toString()) && TextUtils.isEmpty(bindingDialog.editTextPassword.text.toString())) {
                    val toast = Toast.makeText(requireActivity(), "Введите пароль и email", Toast.LENGTH_SHORT)
                    toast.show()
                } else {
                    mAuth!!.signInWithEmailAndPassword(
                        bindingDialog.editTextEmail.text.toString(),
                        bindingDialog.editTextPassword.text.toString()
                    ).addOnCompleteListener(
                        OnCompleteListener { task ->
                            if (task.isSuccessful) {
                                currentUser = mAuth!!.currentUser
                                val user_id: String = currentUser!!.getUid()
                                //проверка на авторизацию
                                prefs.edit().putInt("user", 1).apply()
                                prefs.edit().putString("password_user", bindingDialog.editTextPassword.text.toString()).apply()
                                prefs.edit().putString("email_user", bindingDialog.editTextEmail.text.toString()).apply()

                                myDataBase!!.child(user_id).child("name").get()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            prefs.edit().putString("user_name", task.result.value.toString()).apply()
                                        }
                                    }

                                myDataBase!!.child(user_id).child("target").get()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            prefs.edit().putInt("user_target", task.result.value.toString().toInt()).apply()
                                        }
                                    }

                                myDataBase!!.child(user_id).child("birthday").get()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            prefs.edit().putString("user_birthday", task.result.value.toString()).apply()
                                        }
                                    }

                                myDataBase!!.child(user_id).child("genger").get()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            prefs.edit().putInt("user_gender", task.result.value.toString().toInt()).apply()
                                        }
                                    }

                                myDataBase!!.child(user_id).child("weight").get()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            prefs.edit().putString("user_weight", task.result.value.toString()).apply()
                                        }
                                    }

                                myDataBase!!.child(user_id).child("height").get()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            prefs.edit().putString("user_height", task.result.value.toString()).apply()
                                        }
                                    }

                                startActivity(Intent(activity, MainActivity::class.java))
                            } else {
                                val toast = Toast.makeText(requireActivity(), "Некорректный email или пароль", Toast.LENGTH_SHORT)
                                toast.show()
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