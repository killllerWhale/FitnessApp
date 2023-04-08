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
import com.example.fitnessapp.databinding.DialogTargetBinding
import com.example.fitnessapp.databinding.FrafmentSettingBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingsFragment : Fragment() {

    lateinit var binding: FrafmentSettingBinding
    lateinit var bindingDialog: DialogChangeWeightBinding
    private lateinit var prefs: SharedPreferences
    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null
    private var myDataBase: DatabaseReference? = null
    private lateinit var dialog: Dialog
    private lateinit var dialogTarget: Dialog
    lateinit var bindingDialogTarget: DialogTargetBinding
    private var USER_KEY = "User"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FrafmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        bindingDialog = DialogChangeWeightBinding.inflate(layoutInflater)
        bindingDialogTarget = DialogTargetBinding.inflate(layoutInflater)

        dialog = Dialog(requireContext()).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(bindingDialog.root)
        }

        dialogTarget = BottomSheetDialog(requireContext()).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(bindingDialogTarget.root)
        }

        with(binding.setPurposeText) {
            when (prefs.getInt("user_target", 0)) {
                1 -> text = getString(R.string.be_fit)
                0 -> text = getString(R.string.lose_weight)
                else -> text = getString(R.string.muscles_up)
            }
        }

        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY)
        mAuth = FirebaseAuth.getInstance()

        binding.setNameText.text = prefs.getString("user_name", "0")!!
        prefs.getInt("user_target", 0)
        binding.setBirthText.text = prefs.getString("user_birthday", "0")

        binding.setSexText.text =
            if (prefs.getInt("user_gender", 0) == 1) "мужчина" else "женщина"

        binding.setWeightText.text = prefs.getString("user_weight", "0")
        binding.setHeightText.text = prefs.getString("user_height", "0")

        binding.goBack.setOnClickListener { loadFragment(ProfileFragment()) }

        binding.changeTarget.setOnClickListener { chengrTarget() }

        binding.exit.setOnClickListener {
            prefs.edit().putInt("user", 0).apply()
            startActivity(Intent(activity, EntryActivity::class.java))
        }

        binding.setWeightText.setOnClickListener { dialog.show() }

        bindingDialog.cancellation.setOnClickListener {
            dialog.dismiss()
        }


        bindingDialog.enter.setOnClickListener {
            val weight = bindingDialog.editText3.text.toString()
            if (weight.isEmpty()) {
                toast("Пожалуйста, введите действительный вес")
            } else {
                changeDataBaseWeight("weight", weight)
                binding.setWeightText.text = weight
                prefs.edit().putString("user_weight", weight).apply()
                dialog.dismiss()
            }
        }

    }

    fun chengrTarget() {
        dialogTarget.show()
        bindingDialogTarget.apply {
            beFit.setOnClickListener {
                changeDataBaseTarget("target", 1)
                binding.setPurposeText.text = getString(R.string.be_fit)
                prefs.edit().putInt("user_target", 1).apply()
                dialogTarget.dismiss()
            }
            loseWeight.setOnClickListener {
                changeDataBaseTarget("target", 0)
                binding.setPurposeText.text = getString(R.string.lose_weight)
                prefs.edit().putInt("user_target", 0).apply()
                dialogTarget.dismiss()
            }
            musclesUp.setOnClickListener {
                changeDataBaseTarget("target", 2)
                binding.setPurposeText.text = getString(R.string.muscles_up)
                prefs.edit().putInt("user_target", 2).apply()
                dialogTarget.dismiss()
            }
        }
    }
    private fun changeDataBaseWeight(text: String, textChange: String) {
        mAuth!!.signInWithEmailAndPassword(
            prefs.getString("email_user", "0")!!,
            prefs.getString("password_user", "0")!!
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                currentUser = mAuth!!.currentUser
                val user_id = currentUser!!.uid
                myDataBase!!.child(user_id).child(text)
                    .setValue(textChange)
            }
        }
    }

    private fun changeDataBaseTarget(text: String, textChange: Int) {
        mAuth!!.signInWithEmailAndPassword(
            prefs.getString("email_user", "0")!!,
            prefs.getString("password_user", "0")!!
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                currentUser = mAuth!!.currentUser
                val user_id = currentUser!!.uid
                myDataBase!!.child(user_id).child(text)
                    .setValue(textChange)
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }

    fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, duration).show()

}