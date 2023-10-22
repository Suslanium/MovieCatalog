package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.entity.auth.RegistrationRequest
import com.suslanium.filmus.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(registrationRequest: RegistrationRequest) = authRepository.register(registrationRequest)

}