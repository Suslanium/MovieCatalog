package com.suslanium.filmus.domain.entity

import java.time.LocalDate

data class RegistrationRequest(
    val name: String,
    val userName: String,
    val password: String,
    val email: String,
    val gender: Int,
    val birthDate: LocalDate
)
