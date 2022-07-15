package com.heechan.sunrin_hackaton.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.heechan.sunrin_hackaton.R
import com.heechan.sunrin_hackaton.databinding.ActivityLoginBinding
import com.heechan.sunrin_hackaton.ui.signup.SignUp1Activity

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.btnLoginSubmit.setOnClickListener {
            Log.d("login", viewModel.email.value!!)
            Log.d("login", viewModel.password.value!!)
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