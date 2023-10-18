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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.state.RegistrationData
import com.suslanium.filmus.presentation.state.RegistrationState
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.AuthTextField
import com.suslanium.filmus.presentation.ui.common.AuthTopBar
import com.suslanium.filmus.presentation.ui.common.Constants
import com.suslanium.filmus.presentation.ui.common.Constants.EMPTY_STRING
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.screen.registration.components.SegmentedSelectionButton
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
import com.suslanium.filmus.presentation.viewmodel.RegistrationViewModel
import java.time.format.DateTimeFormatter


@ExperimentalMaterial3Api
private val availableDates = object : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis <= System.currentTimeMillis() && utcTimeMillis >= -2177452800000
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year > 1900
    }
}


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
                            openCredentialsPart = registrationViewModel::openCredentialsPart
                        )

                        RegistrationState.Credentials -> RegistrationCredentialsContent(
                            registrationData = registrationData,
                            setPassword = registrationViewModel::setPassword,
                            setRepeatPassword = registrationViewModel::setRepeatPassword,
                            isPasswordVisible = isPasswordVisible,
                            isRepeatPasswordVisible = isRepeatPasswordVisible,
                            setPasswordVisible = { isPasswordVisible = it },
                            setRepeatPasswordVisible = { isRepeatPasswordVisible = it })
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

@Composable
private fun RegistrationCredentialsContent(
    registrationData: RegistrationData,
    setPassword: (String) -> Unit,
    setRepeatPassword: (String) -> Unit,
    isPasswordVisible: Boolean,
    isRepeatPasswordVisible: Boolean,
    setPasswordVisible: (Boolean) -> Unit,
    setRepeatPasswordVisible: (Boolean) -> Unit,
    register: () -> Unit = {},
    registerButtonIsEnabled: Boolean = true
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthTextField(
            title = stringResource(id = R.string.password),
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
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        AuthTextField(
            title = stringResource(id = R.string.repeat_password),
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
            visualTransformation = if (isRepeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(LoginVerticalSpacing))
        AccentButton(
            onClick = register,
            text = stringResource(id = R.string.register_button),
            modifier = Modifier.fillMaxWidth(),
            enabled = registerButtonIsEnabled
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegistrationPersonalInfoContent(
    registrationData: RegistrationData,
    setName: (String) -> Unit,
    setGender: (Int) -> Unit,
    setLogin: (String) -> Unit,
    setEmail: (String) -> Unit,
    setBirthDate: (Long?) -> Unit,
    dateTimeFormatter: DateTimeFormatter,
    openCredentialsPart: () -> Unit,
    continueButtonIsEnabled: Boolean = true
) {
    var shouldShowDatePickerDialog by remember { mutableStateOf(false) }
    if (shouldShowDatePickerDialog) {
        val datePickerState = rememberDatePickerState(selectableDates = availableDates)
        val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(onDismissRequest = { shouldShowDatePickerDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        shouldShowDatePickerDialog = false
                        setBirthDate(datePickerState.selectedDateMillis)
                    }, enabled = confirmEnabled
                ) {
                    Text(text = stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { shouldShowDatePickerDialog = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthTextField(
            title = stringResource(id = R.string.name),
            value = registrationData.name,
            onValueChange = setName
        )
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        SegmentedSelectionButton(
            title = stringResource(id = R.string.gender), options = listOf(
                stringResource(id = R.string.male), stringResource(id = R.string.female)
            ), selectedIndex = registrationData.gender, onItemSelected = setGender
        )
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        AuthTextField(
            title = stringResource(id = R.string.login),
            value = registrationData.login,
            onValueChange = setLogin,
        )
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        AuthTextField(
            title = stringResource(id = R.string.email),
            value = registrationData.email,
            onValueChange = setEmail
        )
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        AuthTextField(title = stringResource(id = R.string.birthdate),
            value = if (registrationData.birthDate != null) registrationData.birthDate.format(
                dateTimeFormatter
            ).toString() else EMPTY_STRING,
            onValueChange = {},
            trailingIcon = {
                IconButton(onClick = { shouldShowDatePickerDialog = true }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.calendar_icon),
                        contentDescription = null
                    )
                }
            })
        Spacer(modifier = Modifier.height(LoginVerticalSpacing))
        AccentButton(
            onClick = openCredentialsPart,
            text = stringResource(id = R.string.continue_button),
            modifier = Modifier.fillMaxWidth(),
            enabled = continueButtonIsEnabled
        )
    }
}