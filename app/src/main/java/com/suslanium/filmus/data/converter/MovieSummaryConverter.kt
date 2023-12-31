package com.suslanium.filmus.data.converter

import com.suslanium.filmus.data.remote.model.MovieElementModel
import com.suslanium.filmus.data.remote.model.ReviewShortModel
import com.suslanium.filmus.domain.entity.movie.MovieSummary

object MovieSummaryConverter {

    fun convert(from: MovieElementModel, userRating: Int?): MovieSummary =
        with(from) {
            MovieSummary(
                id = id,
                name = name,
                posterUri = poster,
                year = year,
                country = country,
                genres = genres ?: emptyList(),
                rating = calculateRatingByReviews(reviews),
                userRating = userRating
            )
        }

    private fun calculateRatingByReviews(reviews: List<ReviewShortModel>?): Float? {
        if (reviews.isNullOrEmpty()) return null
        return reviews.map { reviewShortModel -> reviewShortModel.rating }
            .reduce { acc, rating -> acc + rating }.toFloat() / reviews.size
    }

}