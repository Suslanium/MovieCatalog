package com.suslanium.filmus.presentation.ui.screen.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.common.Constants
import com.suslanium.filmus.presentation.mapper.ErrorTypeToStringResource
import com.suslanium.filmus.presentation.state.ProfileData
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.FilmusTextField
import com.suslanium.filmus.presentation.ui.common.SecondaryButton
import com.suslanium.filmus.presentation.ui.common.SegmentedSelectionButton
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.ButtonPadding
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.GrayAvatarBackground
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.S15_W600
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.White
import java.time.format.DateTimeFormatter

@Composable
fun ProfileContent(
    profile: ProfileData,
    avatarLinkText: String,
    setName: (String) -> Unit,
    setGender: (Int) -> Unit,
    setEmail: (String) -> Unit,
    setAvatarUri: (String) -> Unit,
    applyChanges: () -> Unit,
    revertChanges: () -> Unit,
    logout: () -> Unit,
    isApplyingChanges: Boolean,
    canApplyChanges: Boolean,
    dateTimeFormatter: DateTimeFormatter,
    startOffsetXProvider: () -> Float,
    setShouldShowDateDialog: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(PaddingMedium))
        GlideImage(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .background(GrayAvatarBackground),
            imageModel = { profile.avatarUri },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect(
                            startOffsetXProvider = startOffsetXProvider,
                            backgroundColor = Gray750,
                            shimmerColor = Accent
                        )
                )
            }, failure = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GrayAvatarBackground), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.no_avatar_icon),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = profile.nickName,
            style = S24_W700,
            color = White,
            textAlign = TextAlign.Center
        )
        TextButton(modifier = Modifier.fillMaxWidth(), contentPadding = ButtonPadding, onClick = logout) {
            Text(text = stringResource(id = R.string.logout), color = Accent, style = S15_W600, textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(PaddingLarge))
        FilmusTextField(
            title = stringResource(id = R.string.email),
            value = profile.email,
            onValueChange = setEmail,
            isError = profile.emailValidationErrorType != null,
            errorMessage = if (profile.emailValidationErrorType != null) ErrorTypeToStringResource.map[profile.emailValidationErrorType]?.let {
                stringResource(id = it)
            } else null,
            enabled = !isApplyingChanges)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(
            title = stringResource(id = R.string.avatar_link),
            value = avatarLinkText,
            onValueChange = setAvatarUri,
            enabled = !isApplyingChanges
        )
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(
            title = stringResource(id = R.string.name),
            value = profile.name,
            onValueChange = setName,
            isError = profile.nameValidationErrorType != null,
            errorMessage = if (profile.nameValidationErrorType != null) ErrorTypeToStringResource.map[profile.nameValidationErrorType]?.let {
                stringResource(id = it)
            } else null,
            enabled = !isApplyingChanges)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        SegmentedSelectionButton(
            title = stringResource(id = R.string.gender),
            options = listOf(
                stringResource(id = R.string.male), stringResource(id = R.string.female)
            ),
            selectedIndex = profile.gender,
            onItemSelected = { if (!isApplyingChanges) setGender(it) }
        )
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(
            title = stringResource(id = R.string.birthdate),
            value = if (profile.birthDate != null) profile.birthDate.format(
                dateTimeFormatter
            ).toString() else Constants.EMPTY_STRING,
            onValueChange = {},
            trailingIcon = {
                IconButton(onClick = { if (!isApplyingChanges) setShouldShowDateDialog(true) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.calendar_icon),
                        contentDescription = null
                    )
                }
            },
            enabled = !isApplyingChanges
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        AccentButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = applyChanges,
            text = stringResource(id = R.string.save),
            hasProgressIndicator = isApplyingChanges,
            enabled = canApplyChanges && !isApplyingChanges
        )
        Spacer(modifier = Modifier.height(VerticalSpacing))
        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = revertChanges,
            text = stringResource(id = R.string.cancel),
            enabled = !isApplyingChanges
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
    }
}