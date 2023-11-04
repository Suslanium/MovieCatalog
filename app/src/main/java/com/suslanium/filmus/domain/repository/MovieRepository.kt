package com.suslanium.filmus.domain.repository

import androidx.paging.PagingData
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface MovieRepository {

    fun getMoviesList(): Flow<PagingData<MovieSummary>>

    suspend fun getMovieDetails(id: UUID): MovieDetails

    suspend fun getFavoriteMoviesList(): List<MovieSummary>

}