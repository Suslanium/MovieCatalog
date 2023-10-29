package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.entity.user.UserProfile
import com.suslanium.filmus.domain.repository.UserRepository

class ChangeUserProfileUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(userProfile: UserProfile) = userRepository.changeUserProfile(userProfile)

}