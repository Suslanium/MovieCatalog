package com.suslanium.filmus.data.repository

import com.suslanium.filmus.data.converter.MovieSummaryConverter
import com.suslanium.filmus.data.datasource.UserDataSource
import com.suslanium.filmus.data.remote.api.FavoriteMoviesApiService
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.data.repository.shared.fetchUserRating
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.domain.repository.FavoritesRepository
import java.util.UUID

class FavoritesRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val favoriteMoviesApiService: FavoriteMoviesApiService,
    private val userDataSource: UserDataSource
): FavoritesRepository {

    override suspend fun getFavoriteMoviesList(): List<MovieSummary> {
        val userId = userDataSource.fetchUserId()
        return favoriteMoviesApiService.getFavoriteMovies().movies?.map { movieElementModel ->
            MovieSummaryConverter.convert(
                movieElementModel, fetchUserRating(movieApiService, movieElementModel, userId)
            )
        } ?: emptyList()
    }

    override suspend fun addFavorite(movieId: UUID) = favoriteMoviesApiService.addFavoriteMovie(movieId.toString())

    override suspend fun removeFavorite(movieId: UUID) = favoriteMoviesApiService.deleteFavoriteMovie(movieId.toString())
}