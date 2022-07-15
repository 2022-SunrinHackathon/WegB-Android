package com.heechan.sunrin_hackaton.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heechan.sunrin_hackaton.data.model.User

class MainViewModel : ViewModel() {
    val userData : MutableLiveData<User> = MutableLiveData()
}