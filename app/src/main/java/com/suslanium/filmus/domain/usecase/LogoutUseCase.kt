package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke() = authRepository.logout()

}