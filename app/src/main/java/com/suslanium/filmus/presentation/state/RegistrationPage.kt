package com.suslanium.filmus.presentation.state

sealed interface RegistrationPage {
    object PersonalInfo : RegistrationPage

    object Credentials : RegistrationPage
}