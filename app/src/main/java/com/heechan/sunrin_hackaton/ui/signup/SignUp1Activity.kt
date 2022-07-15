package com.heechan.sunrin_hackaton.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.heechan.sunrin_hackaton.R
import com.heechan.sunrin_hackaton.data.model.User
import com.heechan.sunrin_hackaton.databinding.ActivityLoginBinding
import com.heechan.sunrin_hackaton.databinding.ActivitySignUp1Binding
import com.heechan.sunrin_hackaton.ui.login.LoginViewModel
import com.heechan.sunrin_hackaton.ui.main.MainActivity

class SignUp1Activity : AppCompatActivity() {
    lateinit var binding: ActivitySignUp1Binding
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore

    val viewModel: SignUp1ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up1)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.btnSignUpClose.setOnClickListener {
            finish()
        }

        binding.btnSignUpSubmit.setOnClickListener {
            val userData = User(
                email = viewModel.email.value!!,
                name = viewModel.nickname.value!!,
                address = "주소값"
            )

            auth.createUserWithEmailAndPassword(userData.email, viewModel.password.value!!)
                .addOnSuccessListener {

                    db.collection("user").add(userData)
                        .addOnSuccessListener {

                            Toast.makeText(this, "환영합니다!", Toast.LENGTH_LONG).show()

                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("userData", userData)

                            startActivity(intent)
                            finish()

                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_LONG).show()
                        }

                }
                .addOnFailureListener {
                    Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_LONG).show()
                }

        }
    }
}