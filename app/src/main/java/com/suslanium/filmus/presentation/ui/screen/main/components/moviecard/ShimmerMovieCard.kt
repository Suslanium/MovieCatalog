package com.suslanium.filmus.presentation.ui.screen.main.components.moviecard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusSmall
import com.suslanium.filmus.presentation.ui.theme.MovieCardImageHeight
import com.suslanium.filmus.presentation.ui.theme.MovieCardImageWidth
import com.suslanium.filmus.presentation.ui.theme.MovieCardNameAndRatingHeight
import com.suslanium.filmus.presentation.ui.theme.S16_W700
import com.suslanium.filmus.presentation.ui.theme.S12_W400
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingNormal

@Composable
fun ShimmerMovieCard(modifier: Modifier = Modifier, shimmerOffset: Float) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .height(MovieCardImageHeight)
                .width(MovieCardImageWidth)
                .clip(RoundedCornerShape(MovieCardCornerRadiusSmall))
                .shimmerEffect(
                    startOffsetX = shimmerOffset,
                    backgroundColor = Gray750,
                    shimmerColor = Accent
                )
        )
        Spacer(modifier = Modifier.width(PaddingNormal))
        Column {
            Box(
                modifier = Modifier
                    .height(MovieCardNameAndRatingHeight)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .height(with(LocalDensity.current) { S16_W700.fontSize.toDp() })
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(MovieCardCornerRadiusMedium)
                        )
                        .shimmerEffect(
                            startOffsetX = shimmerOffset,
                            backgroundColor = Gray750,
                            shimmerColor = Accent
                        )
                )
            }
            Spacer(modifier = Modifier.height(PaddingExtraSmall))
            Box(
                modifier = Modifier
                    .height(with(LocalDensity.current) { S12_W400.fontSize.toDp() })
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
        }
    }
}