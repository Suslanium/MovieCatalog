package com.suslanium.filmus.presentation.ui.screen.registration.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.mapper.ErrorTypeToStringResource
import com.suslanium.filmus.presentation.state.RegistrationData
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.AuthTextField
import com.suslanium.filmus.presentation.ui.common.Constants
import com.suslanium.filmus.presentation.ui.theme.ButtonVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.LoginVerticalSpacing
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationPersonalInfoContent(
    registrationData: RegistrationData,
    setName: (String) -> Unit,
    setGender: (Int) -> Unit,
    setLogin: (String) -> Unit,
    setEmail: (String) -> Unit,
    setBirthDate: (Long?) -> Unit,
    dateTimeFormatter: DateTimeFormatter,
    openCredentialsPart: () -> Unit,
    continueButtonIsEnabled: Boolean
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
        AuthTextField(title = stringResource(id = R.string.name),
            value = registrationData.name,
            onValueChange = setName,
            isError = registrationData.nameValidationErrorType != null,
            errorMessage = if (registrationData.nameValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.nameValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        SegmentedSelectionButton(
            title = stringResource(id = R.string.gender), options = listOf(
                stringResource(id = R.string.male), stringResource(id = R.string.female)
            ), selectedIndex = registrationData.gender, onItemSelected = setGender
        )
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        AuthTextField(title = stringResource(id = R.string.login),
            value = registrationData.login,
            onValueChange = setLogin,
            isError = registrationData.loginValidationErrorType != null,
            errorMessage = if (registrationData.loginValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.loginValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        AuthTextField(title = stringResource(id = R.string.email),
            value = registrationData.email,
            onValueChange = setEmail,
            isError = registrationData.emailValidationErrorType != null,
            errorMessage = if (registrationData.emailValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.emailValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
        AuthTextField(title = stringResource(id = R.string.birthdate),
            value = if (registrationData.birthDate != null) registrationData.birthDate.format(
                dateTimeFormatter
            ).toString() else Constants.EMPTY_STRING,
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