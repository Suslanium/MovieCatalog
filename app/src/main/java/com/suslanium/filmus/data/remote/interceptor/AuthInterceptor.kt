package com.suslanium.filmus.data.remote.interceptor

import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.data.datasource.TokenDataSource
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenDataSource: TokenDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.header(Constants.AUTH_HEADER) == null) {
            tokenDataSource.fetchToken()?.let {
                builder.addHeader(
                    Constants.AUTH_HEADER, "${Constants.BEARER} $it"
                )
            }
        }

        return chain.proceed(builder.build())
    }
}