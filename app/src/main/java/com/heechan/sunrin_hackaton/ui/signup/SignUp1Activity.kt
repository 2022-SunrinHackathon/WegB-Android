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
import com.heechan.sunrin_hackaton.data.repository.AuthRepositoryImpl
import com.heechan.sunrin_hackaton.databinding.ActivityLoginBinding
import com.heechan.sunrin_hackaton.databinding.ActivitySignUp1Binding
import com.heechan.sunrin_hackaton.ui.login.LoginViewModel
import com.heechan.sunrin_hackaton.ui.main.MainActivity
import com.heechan.sunrin_hackaton.ui.main.provider
import com.heechan.sunrin_hackaton.uitl.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUp1Activity : AppCompatActivity() {
    lateinit var binding: ActivitySignUp1Binding
//    lateinit var auth: FirebaseAuth
//    lateinit var db: FirebaseFirestore

    lateinit var authDB : AuthRepositoryImpl

    val viewModel: SignUp1ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up1)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        authDB = AuthRepositoryImpl()

        binding.btnSignUpClose.setOnClickListener {
            finish()
        }

        binding.btnSignUpSubmit.setOnClickListener {
            val userData = User(
                email = viewModel.email.value!!,
                name = viewModel.nickname.value!!,
                address = "주소값"
            )

            CoroutineScope(Dispatchers.IO).launch {
                val result : Int = authDB.signUp(userData, viewModel.password.value!!)

                if(result == Result.ok){
                    withContext(Dispatchers.Main){

                        Toast.makeText(this@SignUp1Activity, "환영합니다!", Toast.LENGTH_LONG).show()
                        finish()

                    }
                }
                else{
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignUp1Activity, "회원가입에 실패 했습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            }


        }
    }
}