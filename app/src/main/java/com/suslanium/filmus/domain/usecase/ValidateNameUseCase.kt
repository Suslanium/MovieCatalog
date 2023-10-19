package com.suslanium.filmus.domain.usecase

import com.suslanium.filmus.domain.repository.ValidationRepository

class ValidateNameUseCase {

    operator fun invoke(name: String) = ValidationRepository.validateName(name)

}