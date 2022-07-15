package com.heechan.sunrin_hackaton.data.remote

import com.heechan.sunrin_hackaton.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("/signup")
    suspend fun signup(
        @Body
        userData : User
    )
}