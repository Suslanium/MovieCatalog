package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.ReviewRepository
import java.util.UUID

class DeleteReviewUseCase(private val reviewRepository: ReviewRepository) {

    suspend operator fun invoke(movieId: UUID, reviewId: UUID) = reviewRepository.deleteReview(movieId, reviewId)

}