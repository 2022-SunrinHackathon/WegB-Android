package com.heechan.sunrin_hackaton.ui.write

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteViewModel : ViewModel() {
    val typeIndex : MutableLiveData<Int> = MutableLiveData(0)
    val img: MutableLiveData<Uri> = MutableLiveData<Uri>()
    val content : MutableLiveData<String> = MutableLiveData("")

    val verti : MutableLiveData<Float> = MutableLiveData()
    val hori : MutableLiveData<Float> = MutableLiveData()

}