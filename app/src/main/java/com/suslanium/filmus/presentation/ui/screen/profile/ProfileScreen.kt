package com.suslanium.filmus.presentation.ui.screen.profile

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.user.UserProfile
import com.suslanium.filmus.presentation.common.Constants
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.FilmusTextField
import com.suslanium.filmus.presentation.ui.common.SecondaryButton
import com.suslanium.filmus.presentation.ui.common.SegmentedSelectionButton
import com.suslanium.filmus.presentation.ui.common.ShimmerTextField
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.common.availableBirthDates
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.ButtonCornerRadius
import com.suslanium.filmus.presentation.ui.theme.ButtonText
import com.suslanium.filmus.presentation.ui.theme.FilmusTheme
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.GrayAvatarBackground
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.White
import java.time.LocalDate
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val profile = UserProfile(
        UUID.randomUUID(),
        "Suslanium",
        "aaaa@aaaa.aaaa",
        "https://static.wikia.nocookie.net/antagonists/images/8/8a/DeathPIB.jpg/revision/latest?cb=20230101223904",
        "Ruslan",
        LocalDate.now(),
        0
    )

    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2.8f,
        targetValue = 2.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    var shouldShowDatePickerDialog by remember { mutableStateOf(false) }
    if (shouldShowDatePickerDialog) {
        val datePickerState = rememberDatePickerState(selectableDates = availableBirthDates)
        val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(onDismissRequest = { shouldShowDatePickerDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        shouldShowDatePickerDialog = false
                        //Set date
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

    ProfileContent(profile, startOffsetX) { shouldShowDatePickerDialog = it }
}

@Composable
fun ShimmerProfileContent(shimmerOffset: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingMedium)
            .verticalScroll(rememberScrollState(), false),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .shimmerEffect(
                    startOffsetX = shimmerOffset,
                    backgroundColor = Gray750,
                    shimmerColor = Accent
                )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .height(with(LocalDensity.current) { S24_W700.fontSize.toDp() })
                .width(100.dp)
                .clip(
                    RoundedCornerShape(MovieCardCornerRadiusMedium)
                )
                .shimmerEffect(
                    startOffsetX = shimmerOffset,
                    backgroundColor = Gray750,
                    shimmerColor = Accent
                )
        )
        Spacer(modifier = Modifier.height(PaddingLarge + PaddingExtraSmall))
        ShimmerTextField(shimmerOffset = shimmerOffset)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        ShimmerTextField(shimmerOffset = shimmerOffset)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        ShimmerTextField(shimmerOffset = shimmerOffset)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        ShimmerTextField(shimmerOffset = shimmerOffset)
        Spacer(modifier = Modifier.height(VerticalSpacing))
        ShimmerTextField(shimmerOffset = shimmerOffset)
        Spacer(modifier = Modifier.height(PaddingLarge))
        Box(modifier = Modifier
            .height(with(LocalDensity.current) { ButtonText.fontSize.toDp() } + PaddingLarge + PaddingSmall)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(ButtonCornerRadius)
            )
            .shimmerEffect(
                startOffsetX = shimmerOffset,
                backgroundColor = Gray750,
                shimmerColor = Accent
            ))
        Spacer(modifier = Modifier.height(19.dp))
        Box(modifier = Modifier
            .height(with(LocalDensity.current) { ButtonText.fontSize.toDp() } + PaddingLarge + PaddingSmall)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(ButtonCornerRadius)
            )
            .shimmerEffect(
                startOffsetX = shimmerOffset,
                backgroundColor = Gray750,
                shimmerColor = Accent
            ))
    }
}

@Composable
private fun ProfileContent(
    profile: UserProfile,
    startOffsetX: Float,
    setShouldShowDateDialog: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingMedium)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            startOffsetX = startOffsetX,
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
            text = profile.nickName.orEmpty(),
            style = S24_W700,
            color = White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        FilmusTextField(
            title = stringResource(id = R.string.email),
            value = profile.email,
            onValueChange = {})
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(
            title = stringResource(id = R.string.avatar_link),
            value = profile.avatarUri.orEmpty(),
            onValueChange = {})
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(
            title = stringResource(id = R.string.name),
            value = profile.name,
            onValueChange = {})
        Spacer(modifier = Modifier.height(VerticalSpacing))
        SegmentedSelectionButton(
            title = stringResource(id = R.string.gender), options = listOf(
                stringResource(id = R.string.male), stringResource(id = R.string.female)
            ), selectedIndex = profile.gender, onItemSelected = {}
        )
        Spacer(modifier = Modifier.height(VerticalSpacing))
        FilmusTextField(title = stringResource(id = R.string.birthdate),
            value = Constants.EMPTY_STRING,
            onValueChange = {},
            trailingIcon = {
                IconButton(onClick = { setShouldShowDateDialog(true) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.calendar_icon),
                        contentDescription = null
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        AccentButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            text = stringResource(id = R.string.save),
            enabled = false
        )
        Spacer(modifier = Modifier.height(VerticalSpacing))
        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            text = stringResource(id = R.string.cancel)
        )
    }
}

@Preview
@Composable
fun ProfilePreview() {
    FilmusTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ProfileScreen()
        }
    }
}