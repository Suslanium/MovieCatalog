package com.suslanium.filmus.presentation.state

sealed interface LaunchEvent {

    object Unauthorized : LaunchEvent

    object Authorized : LaunchEvent

}