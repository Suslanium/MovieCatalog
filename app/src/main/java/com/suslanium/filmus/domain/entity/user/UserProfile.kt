package com.suslanium.filmus.domain.entity.user

import java.time.LocalDate
import java.util.UUID

data class UserProfile(
    val id: UUID,
    val nickName: String?,
    val email: String,
    val avatarUri: String?,
    val name: String,
    val birthDate: LocalDate,
    val gender: Int
)
