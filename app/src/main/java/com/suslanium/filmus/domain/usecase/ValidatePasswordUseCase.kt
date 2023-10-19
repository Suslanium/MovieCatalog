package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.ValidationRepository

class ValidatePasswordUseCase {

    operator fun invoke(password: String) = ValidationRepository.validatePassword(password)

}