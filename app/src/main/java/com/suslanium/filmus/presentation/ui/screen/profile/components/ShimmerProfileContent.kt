package com.suslanium.filmus.presentation.ui.screen.profile.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.presentation.ui.common.ShimmerTextField
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.ButtonCornerRadius
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S15_W600
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing

@Composable
fun ShimmerProfileContent(shimmerOffset: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium)
            .verticalScroll(rememberScrollState(), false),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(PaddingMedium))
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
            .height(with(LocalDensity.current) { S15_W600.fontSize.toDp() } + PaddingLarge + PaddingSmall)
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
            .height(with(LocalDensity.current) { S15_W600.fontSize.toDp() } + PaddingLarge + PaddingSmall)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(ButtonCornerRadius)
            )
            .shimmerEffect(
                startOffsetX = shimmerOffset,
                backgroundColor = Gray750,
                shimmerColor = Accent
            ))
        Spacer(modifier = Modifier.height(PaddingMedium))
    }
}