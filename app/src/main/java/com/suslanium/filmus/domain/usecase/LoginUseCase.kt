package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.entity.auth.LoginRequest
import com.suslanium.filmus.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(loginRequest: LoginRequest) = authRepository.login(loginRequest)

}