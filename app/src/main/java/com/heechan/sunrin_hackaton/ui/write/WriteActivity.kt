package com.heechan.sunrin_hackaton.ui.write

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.protobuf.DescriptorProtos
import com.heechan.sunrin_hackaton.R
import com.heechan.sunrin_hackaton.data.model.Accident
import com.heechan.sunrin_hackaton.data.repository.BoardRepositoryImpl
import com.heechan.sunrin_hackaton.databinding.ActivityWriteBinding
import com.heechan.sunrin_hackaton.ui.main.provider
import com.heechan.sunrin_hackaton.uitl.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityWriteBinding
    val viewModel: WriteViewModel by viewModels()

    lateinit var boardRepository: BoardRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        boardRepository = BoardRepositoryImpl()

    val lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return
    }
    val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    //위도 , 경도
    viewModel.verti.value = userNowLocation?.latitude!!.toFloat()
    viewModel.hori.value = userNowLocation?.longitude!!.toFloat()




        val saogList = listOf(
            "폭우",
            "폭설",
            "산사태",
            "지진",
            "산불",
            "화재",
            "폭발",
        )

        binding.spWriteType.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, saogList)

        binding.spWriteType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.typeIndex.value = p2
            }
            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }

        binding.txtWriteSubmit.setOnClickListener {
            Log.d("upload", "실행1")
            val boardData = Accident(
                type = saogList[viewModel.typeIndex.value!!],
                verti = viewModel.verti.value!!,
                hori = viewModel.hori.value!!,
                desc = viewModel.content.value!!,
                writer = provider.userData.value!!.email,
            )
            Log.d("upload", "실행2")

            Toast.makeText(this@WriteActivity, "작성중입니다...", Toast.LENGTH_LONG).show()

            Log.d("upload", "실행3")
            CoroutineScope(Dispatchers.IO).launch {
                val result = boardRepository.saveBoard(boardData, viewModel.img.value!!)
                Log.d("upload", "실행4")
                withContext(Dispatchers.Main){
                    if(result == Result.ok){
                        Toast.makeText(this@WriteActivity, "작성 완료!", Toast.LENGTH_LONG).show()
                        finish()
                    }
                    else{
                        Toast.makeText(this@WriteActivity, "작성 실패", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


}