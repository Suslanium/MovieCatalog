package com.suslanium.filmus.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.screen.main.components.moviecard.ShimmerMovieCard
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.MoviePosterCarouselHeight
import com.suslanium.filmus.presentation.ui.theme.MovieTitle
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium

@Composable
fun MovieShimmerList(
    shimmerOffset: Float
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState(),
        userScrollEnabled = false
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MoviePosterCarouselHeight)
                    .shimmerEffect(
                        startOffsetX = shimmerOffset,
                        backgroundColor = Gray750,
                        shimmerColor = Accent
                    )
            )
        }
        item {
            Box(
                modifier = Modifier.padding(
                    start = PaddingMedium, end = PaddingMedium, top = PaddingMedium, bottom = 19.dp
                ), contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .height(with(LocalDensity.current) { MovieTitle.fontSize.toDp() })
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
        items(count = 10) {
            ShimmerMovieCard(
                modifier = Modifier.padding(
                    start = PaddingMedium, end = PaddingMedium, bottom = PaddingMedium
                ), shimmerOffset = shimmerOffset
            )
        }
    }
}