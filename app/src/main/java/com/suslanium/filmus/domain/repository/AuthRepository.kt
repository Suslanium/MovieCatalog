package com.suslanium.filmus.domain.repository

import com.suslanium.filmus.domain.entity.auth.LoginRequest
import com.suslanium.filmus.domain.entity.auth.RegistrationRequest

interface AuthRepository {

    suspend fun register(registerRequest: RegistrationRequest)

    suspend fun login(loginRequest: LoginRequest)

    suspend fun hasTokens(): Boolean

}