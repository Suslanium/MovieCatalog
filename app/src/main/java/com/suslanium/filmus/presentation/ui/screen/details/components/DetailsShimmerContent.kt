package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray750

@Composable
fun DetailsShimmerContent(paddingValues: PaddingValues, shimmerOffsetProvider: () -> Float) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .height(497.dp)
            .shimmerEffect(
                startOffsetXProvider = shimmerOffsetProvider,
                backgroundColor = Gray750,
                shimmerColor = Accent
            )
            .background(
                Brush.verticalGradient(colorStops = detailsGradientColorStops)
            )
    )
}