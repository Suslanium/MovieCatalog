package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.ValidationRepository

class ValidateEmailUseCase(
    private val emailIsValid: (String) -> Boolean
) {

    operator fun invoke(email: String) =
        ValidationRepository.validateEmail(email, emailIsValid)

}