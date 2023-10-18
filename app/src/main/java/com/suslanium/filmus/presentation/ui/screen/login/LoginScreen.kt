package com.suslanium.filmus.presentation.ui.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.AuthTextField
import com.suslanium.filmus.presentation.ui.common.AuthTopBar
import com.suslanium.filmus.presentation.ui.common.Constants.AUTH_TAG
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.BottomHint
import com.suslanium.filmus.presentation.ui.theme.ButtonVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.Gray200
import com.suslanium.filmus.presentation.ui.theme.LoginVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.Title
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.ui.theme.WidthFraction
import com.suslanium.filmus.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginData by remember {
        loginViewModel.loginData
    }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val bottomHint = buildAnnotatedString {
        withStyle(
            style = SpanStyle(color = Gray200)
        ) {
            append(stringResource(id = R.string.dont_have_an_account))
            append(" ")
        }

        pushStringAnnotation(AUTH_TAG, AUTH_TAG)
        withStyle(style = SpanStyle(color = Accent)) {
            append(stringResource(id = R.string.register))
        }
        pop()
    }

    Scaffold(containerColor = Background, topBar = {
        AuthTopBar(onNavigateBackClick = { /*TODO*/ })
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(bottom = PaddingMedium),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(WidthFraction),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.log_in_title),
                    color = White,
                    style = Title,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
                AuthTextField(
                    title = stringResource(id = R.string.login),
                    value = loginData.login,
                    onValueChange = loginViewModel::setLogin
                )
                Spacer(modifier = Modifier.height(ButtonVerticalSpacing))

                AuthTextField(
                    title = stringResource(id = R.string.password),
                    value = loginData.password,
                    onValueChange = loginViewModel::setPassword,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.visibility_icon),
                                contentDescription = null
                            )
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(LoginVerticalSpacing))
                AccentButton(
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.sign_in),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
                Spacer(modifier = Modifier.weight(DefaultWeight))
                ClickableText(text = bottomHint, style = BottomHint, onClick = { offset ->
                    bottomHint.getStringAnnotations(tag = AUTH_TAG, start = offset, end = offset)
                        .firstOrNull()?.let {}
                })
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen()
}