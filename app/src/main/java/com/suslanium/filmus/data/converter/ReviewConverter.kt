package com.suslanium.filmus.data.converter

import com.suslanium.filmus.data.remote.model.ReviewModel
import com.suslanium.filmus.domain.entity.movie.Review
import java.time.LocalDateTime
import java.util.UUID

object ReviewConverter {

    fun convertReviewList(reviews: List<ReviewModel>?): List<Review> {
        if (reviews.isNullOrEmpty()) return emptyList()
        return reviews.map { reviewModel ->
            Review(id = UUID.fromString(reviewModel.id),
                rating = reviewModel.rating,
                reviewText = reviewModel.reviewText,
                isAnonymous = reviewModel.isAnonymous,
                creationDateTime = LocalDateTime.parse(
                    reviewModel.createDateTime
                ),
                author = reviewModel.author?.let {
                    UserConverter.convertToUserSummary(it)
                })
        }
    }

}