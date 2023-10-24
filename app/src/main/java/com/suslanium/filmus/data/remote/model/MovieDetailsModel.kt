package com.suslanium.filmus.data.remote.model

data class MovieDetailsModel(
    val id: String,
    val name: String?,
    val poster: String?,
    val year: Int,
    val country: String?,
    val genres: List<GenreModel>?,
    val reviews: List<ReviewModel>?,
    val time: Int,
    val tagline: String?,
    val description: String?,
    val director: String?,
    val budget: Int?,
    val fees: Int?,
    val ageLimit: Int
)
