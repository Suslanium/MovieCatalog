package com.suslanium.filmus.domain.repository

import com.suslanium.filmus.domain.entity.movie.MovieSummary

interface MovieRepository {

    suspend fun getMoviesList(page: Int): List<MovieSummary>

}