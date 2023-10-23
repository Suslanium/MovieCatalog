package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.MovieRepository

class GetMoviesListUseCase(
    private val moviesRepository: MovieRepository
) {

    suspend operator fun invoke(pageNumber: Int) = moviesRepository.getMoviesList(pageNumber)

}