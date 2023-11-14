package com.suslanium.filmus.presentation.state

sealed interface LogoutEvent {

    object Logout : LogoutEvent

}