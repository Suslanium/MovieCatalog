package com.suslanium.filmus.data.repository

import com.suslanium.filmus.data.converter.MovieSummaryConverter
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
) : MovieRepository {

    override suspend fun getMoviesList(page: Int): List<MovieSummary> {
        val moviesPagedList = movieApiService.getMoviesList(page)
        if (moviesPagedList.movies.isNullOrEmpty()) return emptyList()
        return moviesPagedList.movies.map { movieElementModel ->
            MovieSummaryConverter.convert(
                movieElementModel
            )
        }
    }

}