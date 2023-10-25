package com.suslanium.filmus.domain.entity.user

import java.util.UUID

data class UserSummary(
    val userId: UUID,
    val nickName: String?,
    val avatar: String?
)
