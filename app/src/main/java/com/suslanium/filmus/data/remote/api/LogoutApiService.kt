package com.suslanium.filmus.data.remote.api

import com.suslanium.filmus.data.Constants
import retrofit2.http.POST

interface LogoutApiService {

    @POST(Constants.LOGOUT_URL)
    suspend fun logout()

}