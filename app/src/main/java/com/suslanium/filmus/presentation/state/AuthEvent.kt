package com.suslanium.filmus.presentation.state

import androidx.annotation.StringRes

sealed interface AuthEvent {

    data class Error(@StringRes val messageId: Int) : AuthEvent

    object Success : AuthEvent

}