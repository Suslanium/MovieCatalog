package com.suslanium.filmus.data.repository

import com.suslanium.filmus.data.remote.api.ReviewApiService
import com.suslanium.filmus.domain.entity.review.ReviewRequest
import com.suslanium.filmus.domain.repository.ReviewRepository
import java.util.UUID

class ReviewRepositoryImpl(
    private val reviewApiService: ReviewApiService
) : ReviewRepository {

    override suspend fun addReview(movieId: UUID, review: ReviewRequest) =
        reviewApiService.addReview(movieId.toString(), review)

    override suspend fun editReview(movieId: UUID, reviewId: UUID, newReview: ReviewRequest) =
        reviewApiService.editReview(movieId.toString(), reviewId.toString(), newReview)

    override suspend fun deleteReview(movieId: UUID, reviewId: UUID) =
        reviewApiService.deleteReview(movieId.toString(), reviewId.toString())

}