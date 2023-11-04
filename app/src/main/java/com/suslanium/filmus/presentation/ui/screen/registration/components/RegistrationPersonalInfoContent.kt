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
import com.suslanium.filmus.presentation.common.Constants
import com.suslanium.filmus.presentation.mapper.ErrorTypeToStringResource
import com.suslanium.filmus.presentation.state.RegistrationData
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.FilmusTextField
import com.suslanium.filmus.presentation.ui.common.SegmentedSelectionButton
import com.suslanium.filmus.presentation.ui.common.availableBirthDates
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing
import java.time.format.DateTimeFormatter


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
    continueButtonIsEnabled: Boolean,
    registrationFailed: Boolean,
    resetRegistrationError: () -> Unit
) {
    var shouldShowDatePickerDialog by remember { mutableStateOf(false) }
    if (shouldShowDatePickerDialog) {
        val datePickerState = rememberDatePickerState(selectableDates = availableBirthDates)
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
        FilmusTextField(title = stringResource(id = R.string.name),
            value = registrationData.name,
            onValueChange = {
                setName(it)
                resetRegistrationError()
            },
            isError = registrationData.nameValidationErrorType != null || registrationFailed,
            errorMessage = if (registrationData.nameValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.nameValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        SegmentedSelectionButton(
            title = stringResource(id = R.string.gender), options = listOf(
                stringResource(id = R.string.male), stringResource(id = R.string.female)
            ), selectedIndex = registrationData.gender, onItemSelected = setGender
        )
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(title = stringResource(id = R.string.login),
            value = registrationData.login,
            onValueChange = {
                setLogin(it)
                resetRegistrationError()
            },
            isError = registrationData.loginValidationErrorType != null || registrationFailed,
            errorMessage = if (registrationData.loginValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.loginValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(title = stringResource(id = R.string.email),
            value = registrationData.email,
            onValueChange = {
                setEmail(it)
                resetRegistrationError()
            },
            isError = registrationData.emailValidationErrorType != null || registrationFailed,
            errorMessage = if (registrationData.emailValidationErrorType != null) ErrorTypeToStringResource.map[registrationData.emailValidationErrorType]?.let {
                stringResource(
                    id = it
                )
            } else null)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(title = stringResource(id = R.string.birthdate),
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
            },
            isError = registrationFailed)
        Spacer(modifier = Modifier.height(PaddingLarge))
        AccentButton(
            onClick = openCredentialsPart,
            text = stringResource(id = R.string.continue_button),
            modifier = Modifier.fillMaxWidth(),
            enabled = continueButtonIsEnabled
        )
    }
}