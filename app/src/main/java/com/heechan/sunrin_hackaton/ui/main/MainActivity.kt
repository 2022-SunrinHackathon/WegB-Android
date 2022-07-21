package com.heechan.sunrin_hackaton.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.heechan.sunrin_hackaton.R
import com.heechan.sunrin_hackaton.data.Provider
import com.heechan.sunrin_hackaton.ui.write.WriteActivity
import com.heechan.sunrin_hackaton.databinding.ActivityMainBinding
import com.heechan.sunrin_hackaton.ui.login.LoginActivity
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

val provider : Provider = Provider()

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.btnMainGotoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnMainUpload.setOnClickListener {
            if(provider.userData.value == null){
                Toast.makeText(this, "로그인을 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
                

            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }

}