package com.suslanium.filmus.domain.entity.review

data class ReviewRequest(
    val reviewText: String,
    val rating: Int,
    val isAnonymous: Boolean
)
