package com.suslanium.filmus.presentation.mapper

import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.validation.EmailValidationErrorType
import com.suslanium.filmus.domain.entity.validation.LoginValidationErrorType
import com.suslanium.filmus.domain.entity.validation.NameValidationErrorType
import com.suslanium.filmus.domain.entity.validation.PasswordValidationErrorType
import com.suslanium.filmus.domain.entity.validation.RepeatPasswordValidationErrorType

object ErrorTypeToStringResource {

    val map = mapOf(
        NameValidationErrorType.BLANK to R.string.blank_name,
        NameValidationErrorType.TOO_SHORT to R.string.name_too_short,
        LoginValidationErrorType.BLANK to R.string.blank_login,
        LoginValidationErrorType.TOO_SHORT to R.string.login_too_short,
        EmailValidationErrorType.BLANK to R.string.email_blank,
        EmailValidationErrorType.INCORRECT_FORMAT to R.string.email_incorrect_format,
        PasswordValidationErrorType.BLANK to R.string.password_blank,
        PasswordValidationErrorType.TOO_SHORT to R.string.password_too_short,
        RepeatPasswordValidationErrorType.BLANK to R.string.confirm_password_blank,
        RepeatPasswordValidationErrorType.PASSWORDS_DO_NOT_MATCH to R.string.password_dont_match
    )

}