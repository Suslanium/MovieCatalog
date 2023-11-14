package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.FavoritesRepository

class GetFavoriteMoviesListUseCase(
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke() = favoritesRepository.getFavoriteMoviesList()

}