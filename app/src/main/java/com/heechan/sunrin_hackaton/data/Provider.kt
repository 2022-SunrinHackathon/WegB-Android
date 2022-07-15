package com.heechan.sunrin_hackaton.data

import androidx.lifecycle.MutableLiveData
import com.heechan.sunrin_hackaton.data.model.User

class Provider {
    val userData : MutableLiveData<User> = MutableLiveData()
}