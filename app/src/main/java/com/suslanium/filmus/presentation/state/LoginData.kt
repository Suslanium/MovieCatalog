package com.suslanium.filmus.presentation.state

import com.suslanium.filmus.presentation.ui.common.Constants.EMPTY_STRING

data class LoginData(
    val login: String = EMPTY_STRING,
    val password: String = EMPTY_STRING
)
