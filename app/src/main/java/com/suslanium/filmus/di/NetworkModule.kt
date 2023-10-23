package com.suslanium.filmus.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.data.remote.api.AuthApiService
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.data.remote.serialization.LocalDateSerializer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

private fun provideGson(): Gson = GsonBuilder()
    .registerTypeAdapter(LocalDate::class.java, LocalDateSerializer)
    .serializeSpecialFloatingPointValues()
    .create()

private fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

private fun provideAuthApi(gson: Gson): AuthApiService = provideRetrofit(gson).create(AuthApiService::class.java)

private fun provideMovieApi(gson: Gson): MovieApiService = provideRetrofit(gson).create(MovieApiService::class.java)

fun provideNetworkModule() = module {

    factory {
        provideGson()
    }

    single {
        provideAuthApi(get())
    }

    single {
        provideMovieApi(get())
    }

}