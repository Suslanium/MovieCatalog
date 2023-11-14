package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.entity.review.ReviewRequest
import com.suslanium.filmus.domain.repository.ReviewRepository
import java.util.UUID

class AddReviewUseCase(private val reviewRepository: ReviewRepository) {

    suspend operator fun invoke(movieId: UUID, review: ReviewRequest) = reviewRepository.addReview(movieId, review)

}