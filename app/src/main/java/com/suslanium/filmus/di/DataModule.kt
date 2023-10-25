package com.suslanium.filmus.di

import android.content.Context
import com.suslanium.filmus.data.datasource.TokenDataSource
import com.suslanium.filmus.data.datasource.UserDataSource
import com.suslanium.filmus.data.remote.api.UserApiService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun provideTokenDataSource(context: Context): TokenDataSource = TokenDataSource(context)

fun provideUserDataSource(userApiService: UserApiService, context: Context): UserDataSource =
    UserDataSource(userApiService, context)

fun provideDataModule() = module {
    single {
        provideTokenDataSource(androidContext().applicationContext)
    }

    single {
        provideUserDataSource(get(), androidContext().applicationContext)
    }
}