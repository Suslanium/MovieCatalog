package com.suslanium.filmus.data.remote.model

import com.suslanium.filmus.domain.entity.movie.Genre
import java.util.UUID

data class MovieElementModel(
    val id: UUID,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: List<Genre>?,
    val reviews: List<ReviewShortModel>?
)
