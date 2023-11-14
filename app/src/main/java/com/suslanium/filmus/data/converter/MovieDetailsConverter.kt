package com.suslanium.filmus.data.converter

import com.suslanium.filmus.data.remote.model.MovieDetailsModel
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import com.suslanium.filmus.domain.entity.review.Review

object MovieDetailsConverter {

    fun convert(from: MovieDetailsModel, isFavorite: Boolean, userReview: Review?): MovieDetails = with(from) {
        val reviews = reviews?.toMutableList()
        if (userReview != null) {
            reviews?.remove(userReview)
            reviews?.add(0, userReview)
        }
        MovieDetails(
            id = id,
            isFavorite = isFavorite,
            name = name,
            posterUri = poster,
            year = year,
            country = country,
            genres = genres ?: emptyList(),
            userReview = userReview,
            reviews = reviews ?: emptyList(),
            lengthMinutes = time,
            tagLine = tagline,
            description = description,
            director = director,
            budget = budget,
            fees = fees,
            minimalAge = ageLimit,
            rating = calculateRatingByReviews(from.reviews)
        )
    }

    private fun calculateRatingByReviews(reviews: List<Review>?): Float? {
        if (reviews.isNullOrEmpty()) return null
        return reviews.map { review -> review.rating }
            .reduce { acc, rating -> acc + rating }.toFloat() / reviews.size
    }

}