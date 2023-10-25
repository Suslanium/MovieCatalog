package com.suslanium.filmus.data.converter

import com.suslanium.filmus.data.remote.model.MovieDetailsModel
import com.suslanium.filmus.domain.entity.movie.MovieDetails

object MovieDetailsConverter {

    fun convert(from: MovieDetailsModel): MovieDetails = with(from) {
        MovieDetails(
            id = id,
            name = name,
            posterUri = poster,
            year = year,
            country = country,
            genres = genres ?: emptyList(),
            reviews = reviews ?: emptyList(),
            lengthMinutes = time,
            tagLine = tagline,
            description = description,
            director = director,
            budget = budget,
            fees = fees,
            minimalAge = ageLimit
        )
    }

}