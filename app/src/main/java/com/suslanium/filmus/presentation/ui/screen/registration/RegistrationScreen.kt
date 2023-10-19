package com.suslanium.filmus.presentation.ui.screen.registration

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.state.RegistrationState
import com.suslanium.filmus.presentation.ui.common.AuthTopBar
import com.suslanium.filmus.presentation.ui.common.Constants
import com.suslanium.filmus.presentation.ui.common.Constants.EMPTY_STRING
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.screen.registration.components.RegistrationCredentialsContent
import com.suslanium.filmus.presentation.ui.screen.registration.components.RegistrationPersonalInfoContent
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.BottomHint
import com.suslanium.filmus.presentation.ui.theme.ButtonVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.Gray200
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.Title
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.ui.theme.WidthFraction
import com.suslanium.filmus.presentation.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel = viewModel(), navController: NavController
) {
    val registrationData by remember { registrationViewModel.registrationData }
    val registrationState by remember { registrationViewModel.registrationState }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isRepeatPasswordVisible by remember { mutableStateOf(false) }

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
        if (registrationState == RegistrationState.Credentials) registrationViewModel.openPersonalInfoPart() else navController.navigateUp()
    }

    Scaffold(containerColor = Background, topBar = {
        AuthTopBar(onNavigateBackClick = {
            if (registrationState == RegistrationState.Credentials) registrationViewModel.openPersonalInfoPart() else navController.navigateUp()
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
                    style = Title,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
                Crossfade(targetState = registrationState, label = EMPTY_STRING) { state ->
                    when (state) {
                        RegistrationState.PersonalInfo -> RegistrationPersonalInfoContent(
                            registrationData = registrationData,
                            setName = registrationViewModel::setName,
                            setGender = registrationViewModel::setGender,
                            setLogin = registrationViewModel::setLogin,
                            setEmail = registrationViewModel::setEmail,
                            setBirthDate = registrationViewModel::setBirthDate,
                            dateTimeFormatter = registrationViewModel.dateFormat,
                            openCredentialsPart = registrationViewModel::openCredentialsPart,
                            continueButtonIsEnabled = registrationViewModel.personalInfoIsCorrectlyFilled
                        )

                        RegistrationState.Credentials -> RegistrationCredentialsContent(
                            registrationData = registrationData,
                            setPassword = registrationViewModel::setPassword,
                            setRepeatPassword = registrationViewModel::setRepeatPassword,
                            isPasswordVisible = isPasswordVisible,
                            isRepeatPasswordVisible = isRepeatPasswordVisible,
                            setPasswordVisible = { isPasswordVisible = it },
                            setRepeatPasswordVisible = { isRepeatPasswordVisible = it },
                            registerButtonIsEnabled = registrationViewModel.credentialsAreCorrectlyFilled
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(DefaultWeight))
                ClickableText(text = bottomHint, style = BottomHint, onClick = { offset ->
                    bottomHint.getStringAnnotations(
                        tag = Constants.AUTH_TAG, start = offset, end = offset
                    ).firstOrNull()?.let {
                        navController.navigate(FilmusDestinations.LOGIN)
                    }
                })
            }
        }
    }
}



