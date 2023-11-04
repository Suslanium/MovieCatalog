package com.suslanium.filmus.domain.repository

import com.suslanium.filmus.domain.entity.user.UserProfile

interface UserRepository {

    suspend fun getUserProfile(): UserProfile

    suspend fun changeUserProfile(userProfile: UserProfile)

}