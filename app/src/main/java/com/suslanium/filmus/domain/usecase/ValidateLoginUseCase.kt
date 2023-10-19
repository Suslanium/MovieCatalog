package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.entity.validation.LoginValidationErrorType

class ValidateLoginUseCase {

    operator fun invoke(login: String): LoginValidationErrorType? {
        if (login.isBlank()) return LoginValidationErrorType.BLANK
        if (login.length < 4) return LoginValidationErrorType.TOO_SHORT
        return null
    }

}