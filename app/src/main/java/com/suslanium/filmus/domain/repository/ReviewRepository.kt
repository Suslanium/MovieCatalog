package com.suslanium.filmus.domain.repository

import com.suslanium.filmus.domain.entity.review.ReviewRequest
import java.util.UUID

interface ReviewRepository {

    suspend fun addReview(movieId: UUID, review: ReviewRequest)

    suspend fun editReview(movieId: UUID, reviewId: UUID, newReview: ReviewRequest)

    suspend fun deleteReview(movieId: UUID, reviewId: UUID)

}