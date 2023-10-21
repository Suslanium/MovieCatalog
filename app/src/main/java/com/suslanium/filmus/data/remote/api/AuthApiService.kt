package com.suslanium.filmus.data.remote.api

import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.data.remote.model.TokenResponse
import com.suslanium.filmus.domain.entity.LoginRequest
import com.suslanium.filmus.domain.entity.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body body: RegistrationRequest): TokenResponse

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body body: LoginRequest): TokenResponse

}