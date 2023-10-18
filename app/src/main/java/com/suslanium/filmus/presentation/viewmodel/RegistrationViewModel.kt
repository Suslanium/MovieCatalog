package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.suslanium.filmus.presentation.state.RegistrationData
import com.suslanium.filmus.presentation.state.RegistrationState
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class RegistrationViewModel : ViewModel() {

    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val registrationData: State<RegistrationData>
        get() = _registrationData
    private val _registrationData = mutableStateOf(RegistrationData())

    val registrationState: State<RegistrationState>
        get() = _registrationState
    private val _registrationState = mutableStateOf<RegistrationState>(RegistrationState.PersonalInfo)

    fun setName(name: String) {
        _registrationData.value = _registrationData.value.copy(name = name)
    }

    fun setGender(gender: Int) {
        _registrationData.value = _registrationData.value.copy(gender = gender)
    }

    fun setLogin(login: String) {
        _registrationData.value = _registrationData.value.copy(login = login)
    }

    fun setEmail(email: String) {
        _registrationData.value = _registrationData.value.copy(email = email)
    }

    fun setBirthDate(birthDate: Long?) {
        _registrationData.value = _registrationData.value.copy(birthDate = birthDate?.let {
            Instant.ofEpochMilli(it).atZone(
                ZoneId.of("UTC")).toLocalDate()
        })
    }

    fun setPassword(password: String) {
        _registrationData.value = _registrationData.value.copy(password = password)
    }

    fun setRepeatPassword(repeatPassword: String) {
        _registrationData.value = _registrationData.value.copy(repeatPassword = repeatPassword)
    }

    fun openCredentialsPart() {
        _registrationState.value = RegistrationState.Credentials
    }

    fun openPersonalInfoPart() {
        _registrationState.value = RegistrationState.PersonalInfo
    }

}