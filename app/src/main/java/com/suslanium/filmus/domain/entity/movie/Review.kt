package com.suslanium.filmus.domain.entity.movie

import com.suslanium.filmus.domain.entity.user.UserSummary
import java.time.LocalDateTime
import java.util.UUID

data class Review(
    val id: UUID,
    val rating: Int,
    val reviewText: String?,
    val isAnonymous: Boolean,
    val creationDateTime: LocalDateTime,
    val author: UserSummary?
)