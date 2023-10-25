package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.MovieRepository
import java.util.UUID

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: UUID) = movieRepository.getMovieDetails(movieId)

}