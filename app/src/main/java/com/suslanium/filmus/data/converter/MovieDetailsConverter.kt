package com.suslanium.filmus.data.converter

import com.suslanium.filmus.data.remote.model.MovieDetailsModel
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import java.util.UUID

object MovieDetailsConverter {

    fun convert(from: MovieDetailsModel): MovieDetails = with(from) {
        MovieDetails(
            id = UUID.fromString(id),
            name = name,
            posterUri = poster,
            year = year,
            country = country,
            genres = GenreConverter.convertGenreList(genres),
            reviews = ReviewConverter.convertReviewList(reviews),
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