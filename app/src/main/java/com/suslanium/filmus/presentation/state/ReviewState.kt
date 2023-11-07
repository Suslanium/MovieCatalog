package com.suslanium.filmus.presentation.state

sealed interface ReviewState {

    object DialogClosed: ReviewState

    object Editing: ReviewState

    object Deleting: ReviewState

    object Saving: ReviewState

    object Error: ReviewState

}