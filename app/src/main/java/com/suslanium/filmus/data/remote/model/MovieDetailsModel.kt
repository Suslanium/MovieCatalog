package com.suslanium.filmus.data.remote.model

import com.suslanium.filmus.domain.entity.movie.Genre
import com.suslanium.filmus.domain.entity.review.Review
import java.util.UUID

data class MovieDetailsModel(
    val id: UUID,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: List<Genre>?,
    val reviews: List<Review>?,
    val time: Int,
    val tagline: String?,
    val description: String?,
    val director: String?,
    val budget: Int?,
    val fees: Int?,
    val ageLimit: Int
)
