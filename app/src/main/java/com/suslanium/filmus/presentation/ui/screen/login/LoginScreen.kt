package com.suslanium.filmus.presentation.ui.screen.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.common.Constants.AUTH_TAG
import com.suslanium.filmus.presentation.state.AuthEvent
import com.suslanium.filmus.presentation.state.AuthState
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.AuthTopBar
import com.suslanium.filmus.presentation.ui.common.ObserveAsEvents
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.screen.login.components.LoginContent
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.Gray200
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.S14_W500
import com.suslanium.filmus.presentation.ui.theme.S20_W700
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.ui.theme.WidthFraction
import com.suslanium.filmus.presentation.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController
) {
    val loginViewModel: LoginViewModel = koinViewModel()
    val loginData by remember { loginViewModel.loginData }
    val loginState by remember { loginViewModel.loginState }
    var loginErrorMessageId by remember { mutableStateOf<Int?>(null) }
    val focusManager = LocalFocusManager.current

    ObserveAsEvents(flow = loginViewModel.loginEvents) { event ->
        when (event) {
            AuthEvent.Success -> {
                navController.navigate(FilmusDestinations.MAIN) {
                    popUpTo(FilmusDestinations.LOGIN) {
                        inclusive = true
                    }
                }
            }

            is AuthEvent.Error -> loginErrorMessageId = event.messageId
        }
    }

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

    BackHandler {
        if (loginState != AuthState.Loading) navController.popBackStack()
    }

    Scaffold(modifier = Modifier.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }, containerColor = Background, topBar = {
        AuthTopBar(onNavigateBackClick = {
            if (loginState != AuthState.Loading) navController.popBackStack()
        })
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
                    style = S20_W700,
                    textAlign = TextAlign.Center
                )
                LoginContent(loginData = loginData,
                    setLogin = loginViewModel::setLogin,
                    setPassword = loginViewModel::setPassword,
                    loginState = loginState,
                    loginErrorMessageId = loginErrorMessageId,
                    resetLoginError = { loginErrorMessageId = null })

                Spacer(modifier = Modifier.height(PaddingLarge))
                AccentButton(
                    onClick = loginViewModel::login,
                    text = stringResource(id = R.string.sign_in),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = loginViewModel.loginFormIsCorrectlyFilled && loginState != AuthState.Loading,
                    hasProgressIndicator = loginState == AuthState.Loading
                )
                Spacer(modifier = Modifier.weight(DefaultWeight))
                ClickableText(text = bottomHint, style = S14_W500, onClick = { offset ->
                    bottomHint.getStringAnnotations(tag = AUTH_TAG, start = offset, end = offset)
                        .firstOrNull()?.let {
                            if (loginState != AuthState.Loading) navController.navigate(
                                FilmusDestinations.REGISTRATION
                            )
                        }
                })
            }
        }
    }
}

