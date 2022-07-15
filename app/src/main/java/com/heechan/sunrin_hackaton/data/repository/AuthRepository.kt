package com.heechan.sunrin_hackaton.data.repository

import com.heechan.sunrin_hackaton.data.model.User
import com.heechan.sunrin_hackaton.uitl.Result

interface AuthRepository {
    suspend fun signUp(user : User, password : String) : Int
    suspend fun createAccount(user : User, password: String) : Int
    suspend fun saveUserData(user: User) : Int

    suspend fun login(email : String, password : String) : User?
    suspend fun getUserDataByEmail(email : String) : User?
}