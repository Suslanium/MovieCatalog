package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.entity.validation.RepeatPasswordValidationErrorType

class ValidateRepeatPasswordUseCase {

    operator fun invoke(
        password: String, repeatPassword: String
    ): RepeatPasswordValidationErrorType? {
        if (repeatPassword.isBlank()) return RepeatPasswordValidationErrorType.BLANK
        if (password != repeatPassword) return RepeatPasswordValidationErrorType.PASSWORDS_DO_NOT_MATCH
        return null
    }

}