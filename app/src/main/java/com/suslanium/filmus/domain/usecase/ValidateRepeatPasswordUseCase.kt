package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.ValidationRepository

class ValidateRepeatPasswordUseCase {

    operator fun invoke(
        password: String, repeatPassword: String
    ) = ValidationRepository.validateRepeatPassword(password, repeatPassword)

}