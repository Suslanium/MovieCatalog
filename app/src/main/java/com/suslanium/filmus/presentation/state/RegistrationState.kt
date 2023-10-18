package com.suslanium.filmus.presentation.state

sealed interface RegistrationState {
    object PersonalInfo : RegistrationState

    object Credentials : RegistrationState
}