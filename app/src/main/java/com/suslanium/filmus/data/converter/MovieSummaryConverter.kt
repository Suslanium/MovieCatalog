package com.suslanium.filmus.data.converter

import com.suslanium.filmus.data.remote.model.MovieElementModel
import com.suslanium.filmus.data.remote.model.ReviewShortModel
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import java.util.UUID

object MovieSummaryConverter {

    fun convert(from: MovieElementModel): MovieSummary =
        with(from) {
            MovieSummary(
                id = UUID.fromString(id),
                name = name,
                posterUri = poster,
                year = year,
                country = country,
                genres = GenreConverter.convertGenreList(genres),
                rating = calculateRatingByReviews(reviews),
                userRating = null
            )
        }

    private fun calculateRatingByReviews(reviews: List<ReviewShortModel>?): Float? {
        if (reviews.isNullOrEmpty()) return null
        return reviews.map { reviewShortModel -> reviewShortModel.rating }
            .reduce { acc, rating -> acc + rating }.toFloat() / reviews.size
    }

}