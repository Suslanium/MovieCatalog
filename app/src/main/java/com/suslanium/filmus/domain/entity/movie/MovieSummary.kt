package com.suslanium.filmus.domain.entity.movie

import java.util.UUID

data class MovieSummary(
    val id: UUID,
    val name: String?,
    val posterUri: String?,
    val year: Int,
    val country: String?,
    val genres: List<Genre>,
    val rating: Float?,
    val userRating: Float?
)
