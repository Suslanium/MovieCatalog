package com.suslanium.filmus.presentation.state

import com.suslanium.filmus.presentation.ui.common.Constants.EMPTY_STRING
import java.time.LocalDate

data class RegistrationData(
    val name: String = EMPTY_STRING,
    val gender: Int = 0,
    val login: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val birthDate: LocalDate? = null,
    val password: String = EMPTY_STRING,
    val repeatPassword: String = EMPTY_STRING
)
