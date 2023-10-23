package com.suslanium.filmus.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.suslanium.filmus.data.paging.MoviePagingSource
import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService
) : MovieRepository {

    override fun getMoviesList(): Flow<PagingData<MovieSummary>> {
        return Pager(
            config = PagingConfig(pageSize = 6),
            initialKey = 1,
            pagingSourceFactory = {
                MoviePagingSource(movieApiService)
            }
        ).flow
    }

}