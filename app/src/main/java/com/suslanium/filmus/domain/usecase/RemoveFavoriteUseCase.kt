package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.FavoritesRepository
import java.util.UUID

class RemoveFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke(movieId: UUID) = favoritesRepository.removeFavorite(movieId)

}