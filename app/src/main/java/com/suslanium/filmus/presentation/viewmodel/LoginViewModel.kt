package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.suslanium.filmus.presentation.state.LoginData

class LoginViewModel : ViewModel() {

    val loginData: State<LoginData>
        get() = _loginData
    private val _loginData = mutableStateOf(LoginData())

    fun setLogin(login: String) {
        _loginData.value = _loginData.value.copy(login = login)
    }

    fun setPassword(password: String) {
        _loginData.value = _loginData.value.copy(password = password)
    }

}