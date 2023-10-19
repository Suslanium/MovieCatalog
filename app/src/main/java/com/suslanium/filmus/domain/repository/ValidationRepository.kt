package com.suslanium.filmus.domain.repository

import com.suslanium.filmus.domain.entity.validation.EmailValidationErrorType
import com.suslanium.filmus.domain.entity.validation.LoginValidationErrorType
import com.suslanium.filmus.domain.entity.validation.NameValidationErrorType
import com.suslanium.filmus.domain.entity.validation.PasswordValidationErrorType
import com.suslanium.filmus.domain.entity.validation.RepeatPasswordValidationErrorType

object ValidationRepository {

    fun validateEmail(email: String, emailIsValid: (String) -> Boolean): EmailValidationErrorType? {
        if (email.isBlank()) return EmailValidationErrorType.BLANK
        if (!emailIsValid(email)) return EmailValidationErrorType.INCORRECT_FORMAT
        return null
    }

    fun validateLogin(login: String): LoginValidationErrorType? {
        if (login.isBlank()) return LoginValidationErrorType.BLANK
        if (login.length < 4) return LoginValidationErrorType.TOO_SHORT
        return null
    }

    fun validateName(name: String): NameValidationErrorType? {
        if (name.isBlank()) return NameValidationErrorType.BLANK
        if (name.length < 2) return NameValidationErrorType.TOO_SHORT
        return null
    }

    fun validatePassword(password: String): PasswordValidationErrorType? {
        if (password.isBlank()) return PasswordValidationErrorType.BLANK
        if (password.length < 8) return PasswordValidationErrorType.TOO_SHORT
        return null
    }

    fun validateRepeatPassword(
        password: String, repeatPassword: String
    ): RepeatPasswordValidationErrorType? {
        if (repeatPassword.isBlank()) return RepeatPasswordValidationErrorType.BLANK
        if (password != repeatPassword) return RepeatPasswordValidationErrorType.PASSWORDS_DO_NOT_MATCH
        return null
    }

}