package com.suslanium.filmus.presentation.state

import com.suslanium.filmus.domain.entity.validation.EmailValidationErrorType
import com.suslanium.filmus.domain.entity.validation.NameValidationErrorType
import com.suslanium.filmus.presentation.common.Constants
import java.time.LocalDate
import java.util.UUID

data class ProfileData(
    val id: UUID = UUID.randomUUID(),
    val nickName: String = Constants.EMPTY_STRING,
    val email: String = Constants.EMPTY_STRING,
    val emailValidationErrorType: EmailValidationErrorType? = null,
    val avatarUri: String = Constants.EMPTY_STRING,
    val name: String = Constants.EMPTY_STRING,
    val nameValidationErrorType: NameValidationErrorType? = null,
    val birthDate: LocalDate? = null,
    val gender: Int = 0
)
