package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.MovieRepository

class GetFavoriteMoviesListUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke() = movieRepository.getFavoriteMoviesList()

}