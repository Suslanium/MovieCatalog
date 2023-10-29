package com.suslanium.filmus.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.ButtonCornerRadius
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.SubTitleMedium
import com.suslanium.filmus.presentation.ui.theme.S14_W500

@Composable
fun ShimmerTextField(shimmerOffset: Float) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(
            PaddingSmall + 4.dp
        ), horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .height(with(LocalDensity.current) { SubTitleMedium.fontSize.toDp() })
                .width(70.dp)
                .clip(
                    RoundedCornerShape(MovieCardCornerRadiusMedium)
                )
                .shimmerEffect(
                    startOffsetX = shimmerOffset,
                    backgroundColor = Gray750,
                    shimmerColor = Accent
                )
        )
        Box(modifier = Modifier
            .height(with(LocalDensity.current) { S14_W500.fontSize.toDp() } + 32.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(ButtonCornerRadius)
            )
            .shimmerEffect(
                startOffsetX = shimmerOffset,
                backgroundColor = Gray750,
                shimmerColor = Accent
            )
        )
    }
}