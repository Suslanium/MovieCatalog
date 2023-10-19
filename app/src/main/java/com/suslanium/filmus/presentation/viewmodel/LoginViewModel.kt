package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.suslanium.filmus.domain.usecase.ValidateLoginUseCase
import com.suslanium.filmus.domain.usecase.ValidatePasswordUseCase
import com.suslanium.filmus.presentation.state.LoginData

class LoginViewModel(
    private val validateLoginUseCase: ValidateLoginUseCase = ValidateLoginUseCase(),
    private val validatePasswordUseCase: ValidatePasswordUseCase = ValidatePasswordUseCase()
) : ViewModel() {

    val loginFormIsCorrectlyFilled: Boolean
        get() = _loginData.value.login.isNotBlank() && _loginData.value.password.isNotBlank() && loginDataIsValid

    private val loginDataIsValid: Boolean
        get() = _loginData.value.loginValidationErrorType == null && _loginData.value.passwordValidationErrorType == null

    val loginData: State<LoginData>
        get() = _loginData
    private val _loginData = mutableStateOf(LoginData())

    fun setLogin(login: String) {
        val loginValidationResult = validateLoginUseCase(login)
        _loginData.value =
            _loginData.value.copy(login = login, loginValidationErrorType = loginValidationResult)
    }

    fun setPassword(password: String) {
        val passwordValidationResult = validatePasswordUseCase(password)
        _loginData.value = _loginData.value.copy(
            password = password, passwordValidationErrorType = passwordValidationResult
        )
    }

}