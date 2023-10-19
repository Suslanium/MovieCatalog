package com.suslanium.filmus.presentation.state

import com.suslanium.filmus.domain.entity.validation.LoginValidationErrorType
import com.suslanium.filmus.domain.entity.validation.PasswordValidationErrorType
import com.suslanium.filmus.presentation.ui.common.Constants.EMPTY_STRING

data class LoginData(
    val login: String = EMPTY_STRING,
    val loginValidationErrorType: LoginValidationErrorType? = null,
    val password: String = EMPTY_STRING,
    val passwordValidationErrorType: PasswordValidationErrorType? = null
)
