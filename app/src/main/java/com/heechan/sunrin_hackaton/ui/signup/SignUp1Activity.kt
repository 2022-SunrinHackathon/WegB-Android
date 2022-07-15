 package com.heechan.sunrin_hackaton.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.heechan.sunrin_hackaton.R
import com.heechan.sunrin_hackaton.databinding.ActivityLoginBinding
import com.heechan.sunrin_hackaton.databinding.ActivitySignUp1Binding
import com.heechan.sunrin_hackaton.ui.login.LoginViewModel

 class SignUp1Activity : AppCompatActivity() {
     lateinit var binding : ActivitySignUp1Binding

     val viewModel: SignUp1ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up1)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}