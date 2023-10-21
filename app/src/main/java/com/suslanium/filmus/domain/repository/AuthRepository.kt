package com.suslanium.filmus.domain.repository

import com.suslanium.filmus.domain.entity.LoginRequest
import com.suslanium.filmus.domain.entity.RegistrationRequest

interface AuthRepository {

    suspend fun register(registerRequest: RegistrationRequest)

    suspend fun login(loginRequest: LoginRequest)

    suspend fun hasTokens(): Boolean

}