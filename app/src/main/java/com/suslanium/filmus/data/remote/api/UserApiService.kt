package com.suslanium.filmus.data.remote.api

import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.domain.entity.user.UserProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApiService {

    @GET(Constants.USER_PROFILE_URL)
    suspend fun getUserProfile(): UserProfile

    @PUT(Constants.USER_PROFILE_URL)
    suspend fun changeUserProfile(@Body userProfile: UserProfile)

}