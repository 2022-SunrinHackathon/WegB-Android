package com.heechan.sunrin_hackaton.data.repository

import com.heechan.sunrin_hackaton.data.model.User
import com.heechan.sunrin_hackaton.data.remote.AuthService
import com.heechan.sunrin_hackaton.uitl.Result

class AuthRepositoryImpl(val authService: AuthService) : AuthRepository {
    override suspend fun login(userData: User): Int {
        return Result.ok
    }
}