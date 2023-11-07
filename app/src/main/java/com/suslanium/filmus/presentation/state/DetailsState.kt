package com.suslanium.filmus.presentation.state

sealed interface DetailsState {

    object Loading: DetailsState

    object Error: DetailsState

    object Content: DetailsState

}