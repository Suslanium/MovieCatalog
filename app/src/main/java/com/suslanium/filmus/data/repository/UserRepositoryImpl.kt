package com.suslanium.filmus.data.repository

import com.suslanium.filmus.data.datasource.UserDataSource
import com.suslanium.filmus.domain.entity.user.UserProfile
import com.suslanium.filmus.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUserProfile(): UserProfile = userDataSource.getUserProfile()

    override suspend fun changeUserProfile(userProfile: UserProfile) =
        userDataSource.changeUserProfile(userProfile)

}