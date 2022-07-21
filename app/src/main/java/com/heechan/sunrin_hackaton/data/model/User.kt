package com.heechan.sunrin_hackaton.data.model

import java.io.Serializable

data class User(
    val email : String = "",

    val name : String = "",

    val address : String = "",

    val shareCount : Int = 0,

    val levelPoint :  List<Int> = mutableListOf(0, 0),

    val profileImgUrl : String = "http://heestudio.kr/common/img/default_profile.png",

    val accident : List<Accident> = mutableListOf(),
) : Serializable