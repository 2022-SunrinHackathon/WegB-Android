package com.heechan.sunrin_hackaton.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.heechan.sunrin_hackaton.R
import com.heechan.sunrin_hackaton.data.model.User
import com.heechan.sunrin_hackaton.data.repository.AuthRepositoryImpl
import com.heechan.sunrin_hackaton.databinding.ActivityLoginBinding
import com.heechan.sunrin_hackaton.ui.main.MainActivity
import com.heechan.sunrin_hackaton.ui.main.provider
import com.heechan.sunrin_hackaton.ui.signup.SignUp1Activity
import com.heechan.sunrin_hackaton.uitl.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val viewModel: LoginViewModel by viewModels()
    lateinit var authDB: AuthRepositoryImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        authDB = AuthRepositoryImpl()

        binding.btnLoginSubmit.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = authDB.login(viewModel.email.value!!, viewModel.password.value!!)


                withContext(Dispatchers.Main){
                    if(result == null){
                        Toast.makeText(this@LoginActivity, "로그인에 실패했습니다.", Toast.LENGTH_LONG).show()
                    }
                    else{
                        provider.userData.value = result

                        Toast.makeText(this@LoginActivity, "환영합니다!", Toast.LENGTH_LONG).show()

                        finish()
                    }
                }
            }

        }

        binding.txt3.setOnClickListener {
            val intent = Intent(this, SignUp1Activity::class.java)
            startActivity(intent)
        }

//        binding..setOnClickListener {
//            finish()
//        }

    }
}