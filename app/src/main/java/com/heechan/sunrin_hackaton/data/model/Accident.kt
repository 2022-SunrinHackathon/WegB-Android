package com.heechan.sunrin_hackaton.data.model

import android.system.StructTimespec
import java.io.Serializable

data class Accident(
    val type: String = "",
    val verti: Float = 0F,
    val hori: Float = 0F,
    val desc: String = "",
    var imgUrl: String = "",
    val youtubeList: String = "",
    val shareCount: Int = 0,
    val likeCount: Int = 0,
    val writer: String = "",
    val id: Int = 0,
) : Serializable