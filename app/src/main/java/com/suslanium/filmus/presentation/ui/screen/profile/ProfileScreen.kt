package com.suslanium.filmus.presentation.ui.screen.profile

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.state.LogoutEvent
import com.suslanium.filmus.presentation.state.ProfileState
import com.suslanium.filmus.presentation.ui.common.ErrorContent
import com.suslanium.filmus.presentation.ui.common.ObserveAsEvents
import com.suslanium.filmus.presentation.ui.common.availableBirthDates
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.screen.profile.components.ProfileContent
import com.suslanium.filmus.presentation.ui.screen.profile.components.ShimmerProfileContent
import com.suslanium.filmus.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val profileViewModel: ProfileViewModel = koinViewModel()
    val profile by remember { profileViewModel.profileData }
    val profileAvatarLink by profileViewModel.avatarLink.collectAsState()
    val profileState by remember { profileViewModel.profileState }
    val isApplyingChanges by remember { profileViewModel.isApplyingChanges }

    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2.8f,
        targetValue = 2.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    ObserveAsEvents(flow = profileViewModel.logoutEvents) {
        when (it) {
            LogoutEvent.Logout -> navController.navigate(FilmusDestinations.ONBOARDING)
        }
    }

    var shouldShowDatePickerDialog by remember { mutableStateOf(false) }
    if (shouldShowDatePickerDialog) {
        val datePickerState = rememberDatePickerState(selectableDates = availableBirthDates)
        val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(onDismissRequest = { shouldShowDatePickerDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        shouldShowDatePickerDialog = false
                        profileViewModel.setBirthDate(datePickerState.selectedDateMillis)
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

    Crossfade(targetState = profileState, label = "") { state ->
        when (state) {
            ProfileState.Content -> ProfileContent(
                profile = profile,
                avatarLinkText = profileAvatarLink,
                setName = profileViewModel::setName,
                setGender = profileViewModel::setGender,
                setEmail = profileViewModel::setEmail,
                setAvatarUri = profileViewModel::setAvatarUri,
                applyChanges = profileViewModel::applyChanges,
                revertChanges = profileViewModel::cancelChanges,
                isApplyingChanges = isApplyingChanges,
                canApplyChanges = profileViewModel.canApplyChanges,
                dateTimeFormatter = profileViewModel.dateFormat,
                startOffsetXProvider = { startOffsetX },
                setShouldShowDateDialog = { shouldShowDatePickerDialog = it },
                logout = profileViewModel::logout
            )

            ProfileState.Error -> ErrorContent(onRetry = profileViewModel::loadData)
            ProfileState.Loading -> ShimmerProfileContent(shimmerOffsetProvider = { startOffsetX })
        }
    }
}

