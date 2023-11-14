package com.suslanium.filmus.presentation.ui.screen.login.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.mapper.ErrorTypeToStringResource
import com.suslanium.filmus.presentation.state.AuthState
import com.suslanium.filmus.presentation.state.LoginData
import com.suslanium.filmus.presentation.ui.common.FilmusTextField
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.Red
import com.suslanium.filmus.presentation.ui.theme.S15_W400
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing

@Composable
fun LoginContent(
    loginData: LoginData,
    setLogin: (String) -> Unit,
    setPassword: (String) -> Unit,
    loginState: AuthState,
    loginErrorMessageId: Int?,
    resetLoginError: () -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.height(VerticalSpacing))
    FilmusTextField(title = stringResource(id = R.string.login),
        value = loginData.login,
        onValueChange = {
            setLogin(it)
            resetLoginError()
        },
        isError = loginData.loginValidationErrorType != null || loginErrorMessageId != null,
        errorMessage = if (loginData.loginValidationErrorType != null) ErrorTypeToStringResource.map[loginData.loginValidationErrorType]?.let {
            stringResource(
                id = it
            )
        } else null,
        enabled = loginState != AuthState.Loading)
    Spacer(modifier = Modifier.height(VerticalSpacing))

    FilmusTextField(title = stringResource(id = R.string.password),
        value = loginData.password,
        onValueChange = {
            setPassword(it)
            resetLoginError()
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.visibility_icon),
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        isError = loginData.passwordValidationErrorType != null || loginErrorMessageId != null,
        errorMessage = if (loginData.passwordValidationErrorType != null) ErrorTypeToStringResource.map[loginData.passwordValidationErrorType]?.let {
            stringResource(
                id = it
            )
        } else null,
        enabled = loginState != AuthState.Loading)

    if (loginErrorMessageId != null) {
        Spacer(modifier = Modifier.height(PaddingSmall))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = loginErrorMessageId),
            style = S15_W400,
            textAlign = TextAlign.Start,
            color = Red
        )
    }
}