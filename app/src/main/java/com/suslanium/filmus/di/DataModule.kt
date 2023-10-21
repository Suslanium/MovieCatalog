package com.suslanium.filmus.di

import android.content.Context
import com.suslanium.filmus.data.datasource.TokenDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun provideTokenDataSource(context: Context): TokenDataSource = TokenDataSource(context)

fun provideDataModule() = module {
    single {
        provideTokenDataSource(androidContext().applicationContext)
    }
}