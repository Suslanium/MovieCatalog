package com.suslanium.filmus.domain.repository

import com.suslanium.filmus.domain.entity.movie.MovieSummary
import java.util.UUID

interface FavoritesRepository {

    suspend fun getFavoriteMoviesList(): List<MovieSummary>

    suspend fun addFavorite(movieId: UUID)

    suspend fun removeFavorite(movieId: UUID)

}