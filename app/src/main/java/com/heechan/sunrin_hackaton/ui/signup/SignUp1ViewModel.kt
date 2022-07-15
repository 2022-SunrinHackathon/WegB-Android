package com.heechan.sunrin_hackaton.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUp1ViewModel : ViewModel() {
    val nickname : MutableLiveData<String> = MutableLiveData("")
    val email : MutableLiveData<String> = MutableLiveData("")
    val password : MutableLiveData<String> = MutableLiveData("")
    val passwordRe : MutableLiveData<String> = MutableLiveData("")
}