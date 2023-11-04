package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.UserRepository

class GetUserProfileUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() = userRepository.getUserProfile()

}