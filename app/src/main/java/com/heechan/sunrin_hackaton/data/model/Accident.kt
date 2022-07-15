package com.heechan.sunrin_hackaton.data.model

import android.system.StructTimespec
import java.io.Serializable

data class Accident(
    val type : MutableList<Int>,
    val verti : Float,
    val hori : Float,
    val desc : String,
    val imgUrl : String,
    val youtubeList : String,
    val shareCount : Int,
    val likeCount : Int,
    val writer : String,
    val id : Int,
) : Serializable