package com.suslanium.filmus.presentation.ui.screen.favorite.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusSmall
import com.suslanium.filmus.presentation.ui.theme.S14_W500

@Composable
fun ShimmerFavoriteCard(modifier: Modifier, shimmerOffsetProvider: () -> Float) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(205.dp)
                .clip(
                    RoundedCornerShape(MovieCardCornerRadiusSmall)
                )
                .shimmerEffect(
                    startOffsetXProvider = shimmerOffsetProvider,
                    backgroundColor = Gray750,
                    shimmerColor = Accent
                )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .height(with(LocalDensity.current) { S14_W500.fontSize.toDp() })
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(MovieCardCornerRadiusMedium)
                )
                .shimmerEffect(
                    startOffsetXProvider = shimmerOffsetProvider,
                    backgroundColor = Gray750,
                    shimmerColor = Accent
                )
        )
    }
}