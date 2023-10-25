package com.suslanium.filmus.data.remote.api

import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.domain.entity.user.UserProfile
import retrofit2.http.GET

interface UserApiService {

    @GET(Constants.USER_PROFILE_URL)
    suspend fun getUserProfile(): UserProfile

}