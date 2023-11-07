package com.suslanium.filmus.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.data.remote.api.AuthApiService
import com.suslanium.filmus.data.remote.api.FavoriteMoviesApiService
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.data.remote.api.ReviewApiService
import com.suslanium.filmus.data.remote.api.UserApiService
import com.suslanium.filmus.data.remote.interceptor.AuthInterceptor
import com.suslanium.filmus.data.remote.serialization.LocalDateDeserializer
import com.suslanium.filmus.data.remote.serialization.LocalDateSerializer
import com.suslanium.filmus.data.remote.serialization.LocalDateTimeDeserializer
import com.suslanium.filmus.data.remote.serialization.UUIDDeserializer
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import java.util.concurrent.TimeUnit

private fun provideGson(): Gson = GsonBuilder()
    .registerTypeAdapter(LocalDate::class.java, LocalDateSerializer)
    .registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer)
    .registerTypeAdapter(UUID::class.java, UUIDDeserializer)
    .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer)
    .serializeSpecialFloatingPointValues()
    .create()

private fun provideAuthOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(authInterceptor).connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build()

private fun provideRegularOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build()

private fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()

private fun provideAuthApi(gson: Gson, okHttpClient: OkHttpClient): AuthApiService =
    provideRetrofit(gson, okHttpClient).create(AuthApiService::class.java)

private fun provideMovieApi(gson: Gson, okHttpClient: OkHttpClient): MovieApiService =
    provideRetrofit(gson, okHttpClient).create(MovieApiService::class.java)

private fun provideUserApi(gson: Gson, okHttpClient: OkHttpClient): UserApiService =
    provideRetrofit(gson, okHttpClient).create(UserApiService::class.java)

private fun provideFavoriteMoviesApi(gson: Gson, okHttpClient: OkHttpClient): FavoriteMoviesApiService =
    provideRetrofit(gson, okHttpClient).create(FavoriteMoviesApiService::class.java)

private fun provideReviewApi(gson: Gson, okHttpClient: OkHttpClient): ReviewApiService =
    provideRetrofit(gson, okHttpClient).create(ReviewApiService::class.java)

fun provideNetworkModule() = module {

    factory {
        provideGson()
    }

    factory {
        AuthInterceptor(get())
    }

    factory(named("RegularOkHttp")) {
        provideRegularOkHttpClient()
    }

    factory(named("AuthOkHttp")) {
        provideAuthOkHttpClient(get())
    }

    single {
        provideAuthApi(get(), get(named("RegularOkHttp")))
    }

    single {
        provideMovieApi(get(), get(named("RegularOkHttp")))
    }

    single {
        provideUserApi(get(), get(named("AuthOkHttp")))
    }

    single {
        provideFavoriteMoviesApi(get(), get(named("AuthOkHttp")))
    }

    single {
        provideReviewApi(get(), get(named("AuthOkHttp")))
    }

}