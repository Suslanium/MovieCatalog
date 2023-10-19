package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.ValidationRepository

class ValidateLoginUseCase {

    operator fun invoke(login: String) = ValidationRepository.validateLogin(login)

}