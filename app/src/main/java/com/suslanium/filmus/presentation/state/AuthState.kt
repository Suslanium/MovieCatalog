package com.suslanium.filmus.presentation.state

sealed interface AuthState {

    object Content: AuthState

    object Loading: AuthState

}