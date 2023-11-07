package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.auth.LoginRequest
import com.suslanium.filmus.domain.usecase.LoginUseCase
import com.suslanium.filmus.domain.usecase.ValidateLoginUseCase
import com.suslanium.filmus.domain.usecase.ValidatePasswordUseCase
import com.suslanium.filmus.presentation.common.ErrorCodes
import com.suslanium.filmus.presentation.state.AuthEvent
import com.suslanium.filmus.presentation.state.AuthState
import com.suslanium.filmus.presentation.state.LoginData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class LoginViewModel(
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginEventChannel = Channel<AuthEvent>()
    val loginEvents = _loginEventChannel.receiveAsFlow()

    val loginState: State<AuthState>
        get() = _loginState
    private val _loginState = mutableStateOf<AuthState>(AuthState.Content)

    val loginFormIsCorrectlyFilled: Boolean
        get() = _loginData.value.login.isNotBlank() && _loginData.value.password.isNotBlank() && loginDataIsValid

    private val loginDataIsValid: Boolean
        get() = _loginData.value.loginValidationErrorType == null && _loginData.value.passwordValidationErrorType == null

    val loginData: State<LoginData>
        get() = _loginData
    private val _loginData = mutableStateOf(LoginData())

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.BAD_REQUEST -> _loginEventChannel.trySend(AuthEvent.Error(R.string.incorrect_login_data))
                else -> _loginEventChannel.trySend(AuthEvent.Error(R.string.unknown_error))
            }

            else -> _loginEventChannel.trySend(AuthEvent.Error(R.string.unknown_error))
        }
        _loginState.value = AuthState.Content
    }

    fun login() {
        _loginState.value = AuthState.Loading
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            loginUseCase(
                LoginRequest(
                    username = _loginData.value.login,
                    password = _loginData.value.password
                )
            )
            withContext(Dispatchers.Main) {
                _loginEventChannel.send(AuthEvent.Success)
            }
        }
    }

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