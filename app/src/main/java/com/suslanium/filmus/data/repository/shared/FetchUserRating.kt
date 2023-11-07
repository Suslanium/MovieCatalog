package com.suslanium.filmus.data.repository.shared

import com.suslanium.filmus.data.remote.api.MovieApiService
import com.suslanium.filmus.data.remote.model.MovieElementModel
import java.util.UUID

suspend fun fetchUserRating(movieApiService: MovieApiService, movie: MovieElementModel, userId: UUID): Int? {
    val movieDetails = movieApiService.getMovieDetails(movie.id.toString())
    for (review in movieDetails.reviews ?: emptyList()) {
        if (review.author?.userId == userId) {
            return review.rating
        }
    }
    return null
}