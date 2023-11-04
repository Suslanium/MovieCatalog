package com.suslanium.filmus.presentation.state

sealed interface ProfileState {

    object Loading: ProfileState

    object Error: ProfileState

    object Content: ProfileState

}