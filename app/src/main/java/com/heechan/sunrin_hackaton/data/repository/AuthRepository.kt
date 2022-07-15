package com.heechan.sunrin_hackaton.data.repository

import com.heechan.sunrin_hackaton.data.model.User
import com.heechan.sunrin_hackaton.uitl.Result

interface AuthRepository {
    suspend fun login(userData : User) : Int
}