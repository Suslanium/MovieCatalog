package com.suslanium.filmus.domain.entity.movie

import com.suslanium.filmus.domain.entity.review.Review
import java.util.UUID

data class MovieDetails(
    val id: UUID,
    val rating: Float?,
    val isFavorite: Boolean,
    val name: String?,
    val posterUri: String?,
    val year: Int,
    val country: String?,
    val genres: List<Genre>,
    val userReview: Review? = null,
    val reviews: List<Review>,
    val lengthMinutes: Int,
    val tagLine: String?,
    val description: String?,
    val director: String?,
    val budget: Int?,
    val fees: Int?,
    val minimalAge: Int
)
