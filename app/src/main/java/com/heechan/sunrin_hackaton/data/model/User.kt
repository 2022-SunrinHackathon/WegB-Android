package com.heechan.sunrin_hackaton.data.model

import retrofit2.http.Query
import java.io.Serializable

data class User(
    @Query("user_email")
    val email : String = "",

    @Query("user_name")
    val name : String = "",

    @Query("user_address")
    val address : String = "",

    @Query("user_share_count")
    val shareCount : Int = 0,

    @Query("user_level_count")
    val levelPoint :  List<Int> = mutableListOf(0, 0),

    @Query("user_profile_photo")
    val profileImgUrl : String = "http://heestudio.kr/common/img/default_profile.png",

    @Query("user_accident")
    val accident : List<Accident> = mutableListOf(),
) : Serializable