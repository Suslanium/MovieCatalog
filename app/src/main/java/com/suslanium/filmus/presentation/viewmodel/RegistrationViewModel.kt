package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.auth.RegistrationRequest
import com.suslanium.filmus.domain.usecase.RegisterUseCase
import com.suslanium.filmus.domain.usecase.ValidateEmailUseCase
import com.suslanium.filmus.domain.usecase.ValidateLoginUseCase
import com.suslanium.filmus.domain.usecase.ValidateNameUseCase
import com.suslanium.filmus.domain.usecase.ValidatePasswordUseCase
import com.suslanium.filmus.domain.usecase.ValidateRepeatPasswordUseCase
import com.suslanium.filmus.presentation.common.ErrorCodes
import com.suslanium.filmus.presentation.state.AuthEvent
import com.suslanium.filmus.presentation.state.AuthState
import com.suslanium.filmus.presentation.state.RegistrationData
import com.suslanium.filmus.presentation.state.RegistrationPage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class RegistrationViewModel(
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validateEmailDateUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatPasswordUseCase: ValidateRepeatPasswordUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registrationEventChannel = Channel<AuthEvent>()
    val registrationEvents = _registrationEventChannel.receiveAsFlow()

    val registrationState: State<AuthState>
        get() = _registrationState
    private val _registrationState = mutableStateOf<AuthState>(AuthState.Content)

    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    val registrationData: State<RegistrationData>
        get() = _registrationData
    private val _registrationData = mutableStateOf(RegistrationData())

    val registrationPage: State<RegistrationPage>
        get() = _registrationPage
    private val _registrationPage = mutableStateOf<RegistrationPage>(RegistrationPage.PersonalInfo)

    val personalInfoIsCorrectlyFilled: Boolean
        get() = _registrationData.value.name.isNotBlank() && _registrationData.value.login.isNotBlank() && _registrationData.value.email.isNotBlank() && _registrationData.value.birthDate != null && personalInfoValidationSuccessful

    private val personalInfoValidationSuccessful: Boolean
        get() = _registrationData.value.nameValidationErrorType == null && _registrationData.value.loginValidationErrorType == null && _registrationData.value.emailValidationErrorType == null

    val credentialsAreCorrectlyFilled: Boolean
        get() = _registrationData.value.password.isNotBlank() && _registrationData.value.repeatPassword.isNotBlank() && credentialsValidationSuccessful

    private val credentialsValidationSuccessful: Boolean
        get() = _registrationData.value.passwordValidationErrorType == null && _registrationData.value.repeatPasswordValidationErrorType == null

    private val registrationExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.BAD_REQUEST -> _registrationEventChannel.trySend(AuthEvent.Error(R.string.account_already_exists))
                else -> _registrationEventChannel.trySend(AuthEvent.Error(R.string.unknown_error))
            }

            else -> _registrationEventChannel.trySend(AuthEvent.Error(R.string.unknown_error))
        }
        _registrationState.value = AuthState.Content
    }

    fun register() {
        val birthDate = _registrationData.value.birthDate ?: return
        _registrationState.value = AuthState.Loading
        viewModelScope.launch(Dispatchers.IO + registrationExceptionHandler) {
            registerUseCase(
                RegistrationRequest(
                    name = _registrationData.value.name,
                    userName = _registrationData.value.login,
                    password = _registrationData.value.password,
                    email = _registrationData.value.email,
                    gender = _registrationData.value.gender,
                    birthDate = birthDate
                )
            )
            withContext(Dispatchers.Main) {
                _registrationEventChannel.send(AuthEvent.Success)
            }
        }
    }

    fun setName(name: String) {
        val nameValidationResult = validateNameUseCase(name)
        _registrationData.value = _registrationData.value.copy(
            name = name, nameValidationErrorType = nameValidationResult
        )
    }

    fun setGender(gender: Int) {
        _registrationData.value = _registrationData.value.copy(gender = gender)
    }

    fun setLogin(login: String) {
        val loginValidationResult = validateLoginUseCase(login)
        _registrationData.value = _registrationData.value.copy(
            login = login, loginValidationErrorType = loginValidationResult
        )
    }

    fun setEmail(email: String) {
        val emailValidationResult = validateEmailDateUseCase(email)
        _registrationData.value = _registrationData.value.copy(
            email = email, emailValidationErrorType = emailValidationResult
        )
    }

    fun setBirthDate(birthDate: Long?) {
        _registrationData.value = _registrationData.value.copy(birthDate = birthDate?.let {
            Instant.ofEpochMilli(it).atZone(
                ZoneId.of("UTC")
            ).toLocalDate()
        })
    }

    fun setPassword(password: String) {
        val passwordValidationResult = validatePasswordUseCase(password)
        val repeatPasswordValidationResult =
            if (_registrationData.value.repeatPassword.isBlank() && _registrationData.value.repeatPasswordValidationErrorType == null) null else validateRepeatPasswordUseCase(
                password, _registrationData.value.repeatPassword
            )
        _registrationData.value = _registrationData.value.copy(
            password = password,
            passwordValidationErrorType = passwordValidationResult,
            repeatPasswordValidationErrorType = repeatPasswordValidationResult
        )
    }

    fun setRepeatPassword(repeatPassword: String) {
        val repeatPasswordValidationResult =
            validateRepeatPasswordUseCase(_registrationData.value.password, repeatPassword)
        _registrationData.value = _registrationData.value.copy(
            repeatPassword = repeatPassword,
            repeatPasswordValidationErrorType = repeatPasswordValidationResult
        )
    }

    fun openCredentialsPart() {
        if (personalInfoIsCorrectlyFilled) _registrationPage.value = RegistrationPage.Credentials
    }

    fun openPersonalInfoPart() {
        _registrationPage.value = RegistrationPage.PersonalInfo
    }

}