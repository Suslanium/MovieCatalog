package com.suslanium.filmus.presentation.ui.screen.registration

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
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
import com.suslanium.filmus.presentation.common.Constants
import com.suslanium.filmus.presentation.common.Constants.EMPTY_STRING
import com.suslanium.filmus.presentation.state.AuthEvent
import com.suslanium.filmus.presentation.state.AuthState
import com.suslanium.filmus.presentation.state.RegistrationPage
import com.suslanium.filmus.presentation.ui.common.AuthTopBar
import com.suslanium.filmus.presentation.ui.common.ObserveAsEvents
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.screen.registration.components.RegistrationCredentialsContent
import com.suslanium.filmus.presentation.ui.screen.registration.components.RegistrationPersonalInfoContent
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.Gray200
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.S14_W500
import com.suslanium.filmus.presentation.ui.theme.S20_W700
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.ui.theme.WidthFraction
import com.suslanium.filmus.presentation.viewmodel.RegistrationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    navController: NavController
) {
    val registrationViewModel: RegistrationViewModel = koinViewModel()
    val registrationData by remember { registrationViewModel.registrationData }
    val registrationPage by remember { registrationViewModel.registrationPage }
    val registrationState by remember { registrationViewModel.registrationState }
    var registrationErrorMessageId by remember { mutableStateOf<Int?>(null) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isRepeatPasswordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    ObserveAsEvents(flow = registrationViewModel.registrationEvents) { event ->
        when (event) {
            AuthEvent.Success -> navController.navigate(FilmusDestinations.MAIN)
            is AuthEvent.Error -> registrationErrorMessageId = event.messageId
        }
    }

    val bottomHint = buildAnnotatedString {
        withStyle(
            style = SpanStyle(color = Gray200)
        ) {
            append(stringResource(id = R.string.already_have_an_account))
            append(" ")
        }

        pushStringAnnotation(Constants.AUTH_TAG, Constants.AUTH_TAG)
        withStyle(style = SpanStyle(color = Accent)) {
            append(stringResource(id = R.string.log_in_bottom_hint))
        }
        pop()
    }

    BackHandler {
        if (registrationState != AuthState.Loading) {
            if (registrationPage == RegistrationPage.Credentials) registrationViewModel.openPersonalInfoPart() else navController.navigateUp()
        }
    }

    Scaffold(modifier = Modifier.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }, containerColor = Background, topBar = {
        AuthTopBar(onNavigateBackClick = {
            if (registrationState != AuthState.Loading) {
                if (registrationPage == RegistrationPage.Credentials) registrationViewModel.openPersonalInfoPart()
                else if (navController.previousBackStackEntry?.destination?.route == FilmusDestinations.ONBOARDING) navController.navigateUp()
            }
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = PaddingMedium), contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(WidthFraction),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.registration),
                    color = White,
                    style = S20_W700,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(VerticalSpacing))
                Crossfade(targetState = registrationPage, label = EMPTY_STRING) { state ->
                    when (state) {
                        RegistrationPage.PersonalInfo -> RegistrationPersonalInfoContent(
                            registrationData = registrationData,
                            setName = registrationViewModel::setName,
                            setGender = registrationViewModel::setGender,
                            setLogin = registrationViewModel::setLogin,
                            setEmail = registrationViewModel::setEmail,
                            setBirthDate = registrationViewModel::setBirthDate,
                            dateTimeFormatter = Constants.DATE_FORMAT,
                            openCredentialsPart = registrationViewModel::openCredentialsPart,
                            continueButtonIsEnabled = registrationViewModel.personalInfoIsCorrectlyFilled,
                            registrationFailed = registrationErrorMessageId != null,
                            resetRegistrationError = { registrationErrorMessageId = null })

                        RegistrationPage.Credentials -> RegistrationCredentialsContent(
                            registrationData = registrationData,
                            setPassword = registrationViewModel::setPassword,
                            setRepeatPassword = registrationViewModel::setRepeatPassword,
                            isPasswordVisible = isPasswordVisible,
                            isRepeatPasswordVisible = isRepeatPasswordVisible,
                            setPasswordVisible = { isPasswordVisible = it },
                            setRepeatPasswordVisible = { isRepeatPasswordVisible = it },
                            registerButtonIsEnabled = registrationViewModel.credentialsAreCorrectlyFilled && registrationState != AuthState.Loading,
                            resetRegistrationError = { registrationErrorMessageId = null },
                            registrationErrorMessageId = registrationErrorMessageId,
                            register = registrationViewModel::register,
                            registrationState = registrationState
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(DefaultWeight))
                ClickableText(text = bottomHint, style = S14_W500, onClick = { offset ->
                    bottomHint.getStringAnnotations(
                        tag = Constants.AUTH_TAG, start = offset, end = offset
                    ).firstOrNull()?.let {
                        if (registrationState != AuthState.Loading) navController.navigate(
                            FilmusDestinations.LOGIN
                        )
                    }
                })
            }
        }
    }
}



