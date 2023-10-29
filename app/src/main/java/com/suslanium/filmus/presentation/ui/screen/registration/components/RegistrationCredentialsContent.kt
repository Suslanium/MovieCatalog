package com.suslanium.filmus.presentation.ui.screen.registration.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.suslanium.filmus.presentation.state.RegistrationData
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.AuthTextField
import com.suslanium.filmus.presentation.ui.theme.ButtonVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.PaddingMediumLarge
import com.suslanium.filmus.presentation.ui.common.FilmusTextField
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.LoginVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.Red
import com.suslanium.filmus.presentation.ui.theme.S15_W400

@Composable
fun RegistrationCredentialsContent(
    registrationData: RegistrationData,
    setPassword: (String) -> Unit,
    setRepeatPassword: (String) -> Unit,
    isPasswordVisible: Boolean,
    isRepeatPasswordVisible: Boolean,
    setPasswordVisible: (Boolean) -> Unit,
    setRepeatPasswordVisible: (Boolean) -> Unit,
    register: () -> Unit,
    registerButtonIsEnabled: Boolean,
    registrationErrorMessageId: Int?,
    resetRegistrationError: () -> Unit,
    registrationState: AuthState
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilmusTextField(title = stringResource(id = R.string.password),
            enabled = registrationState != AuthState.Loading,
            value = registrationData.password,
            onValueChange = {
                setPassword(it)
                resetRegistrationError()
            },
            trailingIcon = {
                IconButton(onClick = { setPasswordVisible(!isPasswordVisible) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.visibility_icon),
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = registrationData.passwordValidationErrorType != null || registrationErrorMessageId != null,
            errorMessage = if (registrationData.passwordValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.passwordValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(title = stringResource(id = R.string.repeat_password),
            enabled = registrationState != AuthState.Loading,
            value = registrationData.repeatPassword,
            onValueChange = {
                setRepeatPassword(it)
                resetRegistrationError()
            },
            trailingIcon = {
                IconButton(onClick = { setRepeatPasswordVisible(!isRepeatPasswordVisible) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.visibility_icon),
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (isRepeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = registrationData.repeatPasswordValidationErrorType != null || registrationErrorMessageId != null,
            errorMessage = if (registrationData.repeatPasswordValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.repeatPasswordValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        if (registrationErrorMessageId != null) {
            Spacer(modifier = Modifier.height(PaddingSmall))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = registrationErrorMessageId),
                style = S15_W400,
                textAlign = TextAlign.Start,
                color = Red
            )
        }
        Spacer(modifier = Modifier.height(PaddingMediumLarge))
        AccentButton(
            onClick = register,
            text = stringResource(id = R.string.register_button),
            modifier = Modifier.fillMaxWidth(),
            enabled = registerButtonIsEnabled,
            hasProgressIndicator = registrationState == AuthState.Loading
        )
    }
}