package com.suslanium.filmus.di

import android.util.Patterns
import com.suslanium.filmus.data.datasource.TokenDataSource
import com.suslanium.filmus.data.remote.api.AuthApiService
import com.suslanium.filmus.data.repository.AuthRepositoryImpl
import com.suslanium.filmus.domain.repository.AuthRepository
import com.suslanium.filmus.domain.usecase.CheckTokenExistenceUseCase
import com.suslanium.filmus.domain.usecase.LoginUseCase
import com.suslanium.filmus.domain.usecase.RegisterUseCase
import com.suslanium.filmus.domain.usecase.ValidateEmailUseCase
import com.suslanium.filmus.domain.usecase.ValidateLoginUseCase
import com.suslanium.filmus.domain.usecase.ValidateNameUseCase
import com.suslanium.filmus.domain.usecase.ValidatePasswordUseCase
import com.suslanium.filmus.domain.usecase.ValidateRepeatPasswordUseCase
import org.koin.dsl.module

fun provideAuthRepository(authApiService: AuthApiService, tokenDataSource: TokenDataSource): AuthRepository = AuthRepositoryImpl(authApiService, tokenDataSource)

fun provideDomainModule() = module {
    single {
        provideAuthRepository(get(), get())
    }

    factory {
        CheckTokenExistenceUseCase(get())
    }

    factory {
        LoginUseCase(get())
    }

    factory {
        RegisterUseCase(get())
    }

    factory {
        ValidateEmailUseCase {
            Patterns.EMAIL_ADDRESS.matcher(
                it
            ).matches()
        }
    }

    factory {
        ValidateLoginUseCase()
    }

    factory {
        ValidateNameUseCase()
    }

    factory {
        ValidatePasswordUseCase()
    }

    factory {
        ValidateRepeatPasswordUseCase()
    }
}