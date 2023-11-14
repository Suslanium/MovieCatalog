package com.suslanium.filmus.di

import android.util.Patterns
import com.suslanium.filmus.data.datasource.TokenDataSource
import com.suslanium.filmus.data.datasource.UserDataSource
import com.suslanium.filmus.data.remote.api.AuthApiService
import com.suslanium.filmus.data.remote.api.FavoriteMoviesApiService
import com.suslanium.filmus.data.remote.api.LogoutApiService
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.data.remote.api.ReviewApiService
import com.suslanium.filmus.data.repository.AuthRepositoryImpl
import com.suslanium.filmus.data.repository.FavoritesRepositoryImpl
import com.suslanium.filmus.data.repository.MovieRepositoryImpl
import com.suslanium.filmus.data.repository.ReviewRepositoryImpl
import com.suslanium.filmus.data.repository.UserRepositoryImpl
import com.suslanium.filmus.domain.repository.AuthRepository
import com.suslanium.filmus.domain.repository.FavoritesRepository
import com.suslanium.filmus.domain.repository.MovieRepository
import com.suslanium.filmus.domain.repository.ReviewRepository
import com.suslanium.filmus.domain.repository.UserRepository
import com.suslanium.filmus.domain.usecase.AddFavoriteUseCase
import com.suslanium.filmus.domain.usecase.AddReviewUseCase
import com.suslanium.filmus.domain.usecase.ChangeUserProfileUseCase
import com.suslanium.filmus.domain.usecase.CheckTokenExistenceUseCase
import com.suslanium.filmus.domain.usecase.DeleteReviewUseCase
import com.suslanium.filmus.domain.usecase.EditReviewUseCase
import com.suslanium.filmus.domain.usecase.GetFavoriteMoviesListUseCase
import com.suslanium.filmus.domain.usecase.GetMovieDetailsUseCase
import com.suslanium.filmus.domain.usecase.GetMoviesListUseCase
import com.suslanium.filmus.domain.usecase.GetUserProfileUseCase
import com.suslanium.filmus.domain.usecase.LoginUseCase
import com.suslanium.filmus.domain.usecase.LogoutUseCase
import com.suslanium.filmus.domain.usecase.RegisterUseCase
import com.suslanium.filmus.domain.usecase.RemoveFavoriteUseCase
import com.suslanium.filmus.domain.usecase.ValidateEmailUseCase
import com.suslanium.filmus.domain.usecase.ValidateLoginUseCase
import com.suslanium.filmus.domain.usecase.ValidateNameUseCase
import com.suslanium.filmus.domain.usecase.ValidatePasswordUseCase
import com.suslanium.filmus.domain.usecase.ValidateRepeatPasswordUseCase
import org.koin.dsl.module

fun provideAuthRepository(
    authApiService: AuthApiService, logoutApiService: LogoutApiService, userDataSource: UserDataSource, tokenDataSource: TokenDataSource
): AuthRepository = AuthRepositoryImpl(authApiService, logoutApiService, userDataSource, tokenDataSource)

fun provideMovieRepository(movieApiService: MovieApiService, favoriteMoviesApiService: FavoriteMoviesApiService, userDataSource: UserDataSource): MovieRepository =
    MovieRepositoryImpl(movieApiService, favoriteMoviesApiService, userDataSource)

fun provideUserRepository(userDataSource: UserDataSource): UserRepository =
    UserRepositoryImpl(userDataSource)

fun provideFavoritesRepository(movieApiService: MovieApiService, favoriteMoviesApiService: FavoriteMoviesApiService, userDataSource: UserDataSource): FavoritesRepository =
    FavoritesRepositoryImpl(movieApiService, favoriteMoviesApiService, userDataSource)

fun provideReviewRepository(reviewApiService: ReviewApiService): ReviewRepository =
    ReviewRepositoryImpl(reviewApiService)

fun provideDomainModule() = module {
    single {
        provideAuthRepository(get(), get(), get(), get())
    }

    single {
        provideMovieRepository(get(), get(), get())
    }

    single {
        provideUserRepository(get())
    }

    single {
        provideFavoritesRepository(get(), get(), get())
    }

    single {
        provideReviewRepository(get())
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

    factory {
        GetMoviesListUseCase(get())
    }

    factory {
        GetMovieDetailsUseCase(get())
    }

    factory {
        GetUserProfileUseCase(get())
    }

    factory {
        GetFavoriteMoviesListUseCase(get())
    }

    factory {
        ChangeUserProfileUseCase(get())
    }

    factory {
        AddFavoriteUseCase(get())
    }

    factory {
        RemoveFavoriteUseCase(get())
    }

    factory {
        AddReviewUseCase(get())
    }

    factory {
        EditReviewUseCase(get())
    }

    factory {
        DeleteReviewUseCase(get())
    }

    factory {
        LogoutUseCase(get())
    }
}