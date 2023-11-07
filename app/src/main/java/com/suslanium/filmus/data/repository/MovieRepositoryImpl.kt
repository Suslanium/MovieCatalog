package com.suslanium.filmus.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.suslanium.filmus.data.converter.MovieDetailsConverter
import com.suslanium.filmus.data.datasource.UserDataSource
import com.suslanium.filmus.data.paging.MoviePagingSource
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val userDataSource: UserDataSource
) : MovieRepository {

    override fun getMoviesList(): Flow<PagingData<MovieSummary>> {
        return Pager(config = PagingConfig(pageSize = 6), initialKey = 1, pagingSourceFactory = {
            MoviePagingSource(movieApiService, userDataSource)
        }).flow
    }

    override suspend fun getMovieDetails(id: UUID): MovieDetails =
        MovieDetailsConverter.convert(movieApiService.getMovieDetails(id.toString()))

}