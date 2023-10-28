package com.suslanium.filmus.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.suslanium.filmus.data.converter.MovieDetailsConverter
import com.suslanium.filmus.data.converter.MovieSummaryConverter
import com.suslanium.filmus.data.datasource.UserDataSource
import com.suslanium.filmus.data.paging.MoviePagingSource
import com.suslanium.filmus.data.remote.api.FavoriteMoviesApiService
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.data.remote.model.MovieElementModel
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val favoriteMoviesApiService: FavoriteMoviesApiService,
    private val userDataSource: UserDataSource
) : MovieRepository {

    override fun getMoviesList(): Flow<PagingData<MovieSummary>> {
        return Pager(config = PagingConfig(pageSize = 6), initialKey = 1, pagingSourceFactory = {
            MoviePagingSource(movieApiService, userDataSource, this::fetchUserRating)
        }).flow
    }

    override suspend fun getMovieDetails(id: UUID): MovieDetails =
        MovieDetailsConverter.convert(movieApiService.getMovieDetails(id.toString()))

    override suspend fun getFavoriteMoviesList(): List<MovieSummary> {
        val userId = userDataSource.fetchUserId()
        return favoriteMoviesApiService.getFavoriteMovies().movies?.map { movieElementModel ->
            MovieSummaryConverter.convert(
                movieElementModel, fetchUserRating(movieApiService, movieElementModel, userId)
            )
        } ?: emptyList()
    }

    private suspend fun fetchUserRating(movieApiService: MovieApiService, movie: MovieElementModel, userId: UUID): Int? {
        val movieDetails = movieApiService.getMovieDetails(movie.id.toString())
        for (review in movieDetails.reviews ?: emptyList()) {
            if (review.author?.userId == userId) {
                return review.rating
            }
        }
        return null
    }

}