package com.suslanium.filmus.presentation.ui.screen.registration.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.mapper.ErrorTypeToStringResource
import com.suslanium.filmus.presentation.state.RegistrationData
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.AuthTextField
import com.suslanium.filmus.presentation.ui.theme.ButtonVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.LoginVerticalSpacing

@Composable
fun RegistrationCredentialsContent(
    registrationData: RegistrationData,
    setPassword: (String) -> Unit,
    setRepeatPassword: (String) -> Unit,
    isPasswordVisible: Boolean,
    isRepeatPasswordVisible: Boolean,
    setPasswordVisible: (Boolean) -> Unit,
    setRepeatPasswordVisible: (Boolean) -> Unit,
    register: () -> Unit = {},
    registerButtonIsEnabled: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthTextField(title = stringResource(id = R.string.password),
            value = registrationData.password,
            onValueChange = setPassword,
            trailingIcon = {
                IconButton(onClick = { setPasswordVisible(!isPasswordVisible) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.visibility_icon),
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = registrationData.passwordValidationErrorType != null,
            errorMessage = if (registrationData.passwordValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.passwordValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        AuthTextField(title = stringResource(id = R.string.repeat_password),
            value = registrationData.repeatPassword,
            onValueChange = setRepeatPassword,
            trailingIcon = {
                IconButton(onClick = { setRepeatPasswordVisible(!isRepeatPasswordVisible) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.visibility_icon),
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (isRepeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = registrationData.repeatPasswordValidationErrorType != null,
            errorMessage = if (registrationData.repeatPasswordValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.repeatPasswordValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(LoginVerticalSpacing))
        AccentButton(
            onClick = register,
            text = stringResource(id = R.string.register_button),
            modifier = Modifier.fillMaxWidth(),
            enabled = registerButtonIsEnabled
        )
    }
}