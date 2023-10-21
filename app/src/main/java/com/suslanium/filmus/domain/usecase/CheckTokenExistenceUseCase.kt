package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.AuthRepository

class CheckTokenExistenceUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() = authRepository.hasTokens()

}