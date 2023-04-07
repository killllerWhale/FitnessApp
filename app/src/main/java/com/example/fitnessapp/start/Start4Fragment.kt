package com.example.fitnessapp.start

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitnessapp.activity.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.profile.User
import com.example.fitnessapp.databinding.DialogErrorBinding
import com.example.fitnessapp.databinding.FragmentStart4Binding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Start4Fragment : Fragment(){

    private lateinit var binding: FragmentStart4Binding
    lateinit var bindingDialog: DialogErrorBinding
    private val USER_KEY = "User"
    var myDataBase: DatabaseReference? = null
    var mAuth: FirebaseAuth? = null
    lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStart4Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingDialog = DialogErrorBinding.inflate(layoutInflater)
        val context = requireContext()

        dialog = Dialog(requireContext()).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(bindingDialog.root)
        }

        binding.checkProblem.isEnabled = false
        binding.checkProblem0.isEnabled = false

        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY)
        mAuth = FirebaseAuth.getInstance()


        binding.run {
            listOf(start0, start1, start2, start3, goBack).forEach {
                it.setOnClickListener { view ->
                    when (view.id) {
                        R.id.start0 -> loadFragment(Start0Fragment())
                        R.id.start1 -> loadFragment(Start1Fragment())
                        R.id.start2 -> loadFragment(Start2Fragment())
                        R.id.start3 -> loadFragment(Start3Fragment())
                        R.id.go_back -> loadFragment(Start3Fragment())
                    }
                }
            }
            next.setOnClickListener {
                if (editTextName.text.isNotEmpty() && editTextEmail.text.isNotEmpty() && editTextPassword.text.isNotEmpty() && editTextPasswordRepeat.text.isNotEmpty() && (editTextPassword.text.toString() == editTextPasswordRepeat.text.toString())) {
                    if (capchaCheck()) {
                        registration()
                    }
                } else {
                    if (editTextName.text.isEmpty() || editTextEmail.text.isEmpty() || editTextPassword.text.isEmpty() || editTextPasswordRepeat.text.isEmpty()) {
                        val toast = Toast.makeText(requireActivity(), "Введите данные", Toast.LENGTH_SHORT)
                        toast.show()
                    } else {
                        val toast = Toast.makeText(requireActivity(), "Пароли не совпадают", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
            }
        }

        binding.checkProblem.setOnClickListener {
            dialog.show()
        }

        binding.checkProblem0.setOnClickListener {
            dialog.show()
        }

    }



    private fun capchaCheck(): Boolean {
        val emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex()
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,}$".toRegex()

        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        return if (emailPattern.matches(email)) {
            binding.checkProblem.isEnabled = false
            binding.checkProblem.setBackgroundResource(R.color.color_background)
            if (passwordPattern.matches(password)) {
                binding.checkProblem0.isEnabled = false
                binding.checkProblem0.setBackgroundResource(R.color.color_background)
                true
            }else{
                creteMassenge(1)
                toast("Некорректный пароль")
                false
            }
        } else {
            creteMassenge(0)
            toast("Некорректный email")
            false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun creteMassenge(flag: Int){
        if (flag == 0){
            binding.checkProblem.isEnabled = true
            binding.checkProblem.setBackgroundResource(R.drawable.selected_item_red)
            bindingDialog.textView10.text = "Для ввода корректного email необходимо следовать следующим правилам:\n" +
                    "\n" +
                    "1. email должен содержать символ \"@\";\n" +
                    "2. email должен содержать доменную зону, состоящую не менее, чем из двух символов, например \".com\", \".org\", \".edu\" и т.д.;\n" +
                    "3. email не должен содержать пробелов, специальных символов (кроме символа \"@\") или русских букв;\n" +
                    "4. email должен начинаться с буквы или цифры."
        }else{
            binding.checkProblem0.isEnabled = true
            binding.checkProblem0.setBackgroundResource(R.drawable.selected_item_red)
            bindingDialog.textView10.text = "При создании пароля необходимо учитывать следующие условия:\n" +
                    "\n" +
                    "1. Длина пароля должна быть не менее 8 символов\n" +
                    "2. Пароль должен содержать хотя бы одну цифру\n" +
                    "3. Пароль должен содержать хотя бы одну прописную букву\n" +
                    "4. Пароль должен содержать хотя бы одну заглавную букву\n" +
                    "5. Пароль должен содержать хотя бы один из символов @#\$%^&+=\n" +
                    "6. Пароль не должен содержать пробелов\n" +
                    "7. Вы можете использовать комбинацию цифр, букв и символов, чтобы создать надежный пароль, который будет защищать ваши личные данные. Пожалуйста, убедитесь, что ваш пароль соответствует этим условиям."
        }

    }

    private fun registration() {
        val prefs = requireContext().getSharedPreferences("themes", Context.MODE_PRIVATE)
        val id = myDataBase?.key
        mAuth!!.createUserWithEmailAndPassword(
            binding.editTextEmail.text.toString(),
            binding.editTextPassword.text.toString()
        )
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    prefs.edit().putString("user_name", binding.editTextName.text.toString()).apply()
                    val user: FirebaseUser = mAuth!!.getCurrentUser()!!
                    val newUser = User(
                        id.toString(),
                        binding.editTextName.text.toString(),
                        binding.editTextPassword.text.toString(),
                        binding.editTextEmail.text.toString(),
                        prefs.getInt("user_target", 0),
                        prefs.getString("user_birthday", "0")!!,
                        prefs.getInt("user_gender", 0),
                        prefs.getString("user_weight", "0")!!,
                        prefs.getString("user_height", "0")!!
                    )
                    myDataBase!!.child(user.uid).setValue(newUser)
                    //проверка на авторизацию
                    prefs.edit().putString("password_user", binding.editTextPassword.text.toString()).apply()
                    prefs.edit().putString("email_user", binding.editTextEmail.text.toString()).apply()
                    prefs.edit().putInt("user", 1).apply()
                    startActivity(Intent(activity, MainActivity::class.java))
                } else {
                    toast("Сейчас на сервере неполадки, попробуйте позже")
                }
            })

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, duration).show()
}