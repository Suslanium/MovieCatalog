package com.suslanium.filmus.data.converter

import com.suslanium.filmus.data.remote.model.UserShortModel
import com.suslanium.filmus.domain.entity.user.UserSummary
import java.util.UUID

object UserConverter {

    fun convertToUserSummary(from: UserShortModel): UserSummary =
        with(from) {
            UserSummary(
                userId = UUID.fromString(userId),
                nickName = nickName,
                avatar = avatar
            )
        }

}