package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.MovieRepository

class GetMoviesListUseCase(
    private val moviesRepository: MovieRepository
) {

    operator fun invoke() = moviesRepository.getMoviesList()

}