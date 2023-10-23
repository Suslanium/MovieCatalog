package com.suslanium.filmus.domain.repository

import androidx.paging.PagingData
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesList(): Flow<PagingData<MovieSummary>>

}