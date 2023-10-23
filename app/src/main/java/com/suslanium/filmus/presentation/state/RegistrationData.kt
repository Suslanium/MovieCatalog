package com.suslanium.filmus.presentation.state

import com.suslanium.filmus.domain.entity.validation.EmailValidationErrorType
import com.suslanium.filmus.domain.entity.validation.LoginValidationErrorType
import com.suslanium.filmus.domain.entity.validation.NameValidationErrorType
import com.suslanium.filmus.domain.entity.validation.PasswordValidationErrorType
import com.suslanium.filmus.domain.entity.validation.RepeatPasswordValidationErrorType
import com.suslanium.filmus.presentation.common.Constants.EMPTY_STRING
import java.time.LocalDate

data class RegistrationData(
    val name: String = EMPTY_STRING,
    val nameValidationErrorType: NameValidationErrorType? = null,
    val gender: Int = 0,
    val login: String = EMPTY_STRING,
    val loginValidationErrorType: LoginValidationErrorType? = null,
    val email: String = EMPTY_STRING,
    val emailValidationErrorType: EmailValidationErrorType? = null,
    val birthDate: LocalDate? = null,
    val password: String = EMPTY_STRING,
    val passwordValidationErrorType: PasswordValidationErrorType? = null,
    val repeatPassword: String = EMPTY_STRING,
    val repeatPasswordValidationErrorType: RepeatPasswordValidationErrorType? = null
)
