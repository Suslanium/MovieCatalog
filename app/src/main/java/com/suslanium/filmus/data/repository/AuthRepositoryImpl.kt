package com.suslanium.filmus.data.repository

import com.suslanium.filmus.data.datasource.TokenDataSource
import com.suslanium.filmus.data.datasource.UserDataSource
import com.suslanium.filmus.data.remote.api.AuthApiService
import com.suslanium.filmus.data.remote.api.LogoutApiService
import com.suslanium.filmus.domain.entity.auth.LoginRequest
import com.suslanium.filmus.domain.entity.auth.RegistrationRequest
import com.suslanium.filmus.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val logoutApiService: LogoutApiService,
    private val userDataSource: UserDataSource,
    private val tokenDataSource: TokenDataSource
): AuthRepository {

    override suspend fun register(registerRequest: RegistrationRequest) {
        val tokenResponse = authApiService.register(registerRequest)
        tokenDataSource.saveToken(tokenResponse.token)
    }

    override suspend fun login(loginRequest: LoginRequest) {
        val tokenResponse = authApiService.login(loginRequest)
        tokenDataSource.saveToken(tokenResponse.token)
    }

    override suspend fun logout() {
        userDataSource.removeSavedUserId()
        tokenDataSource.deleteToken()
        logoutApiService.logout()
    }

    override suspend fun hasTokens(): Boolean {
        return tokenDataSource.fetchToken() != null
    }

}