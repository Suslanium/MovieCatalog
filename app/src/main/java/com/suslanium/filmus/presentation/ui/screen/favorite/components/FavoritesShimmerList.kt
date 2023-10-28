package com.suslanium.filmus.presentation.ui.screen.favorite.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingMediumLarge
import com.suslanium.filmus.presentation.ui.theme.S24_W700

@Composable
fun FavoritesShimmerList(shimmerOffset: Float) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = rememberLazyListState(),
        userScrollEnabled = false,
        verticalArrangement = Arrangement.spacedBy(
            PaddingMediumLarge
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(PaddingMedium))
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
            Spacer(modifier = Modifier.height(PaddingExtraSmall))
        }
        items(count = 5) { rowIndex ->
            if ((rowIndex + 1) % 2 == 0) {
                ShimmerFavoriteCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PaddingMedium),
                    shimmerOffset = shimmerOffset
                )
            } else {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ShimmerFavoriteCard(
                        modifier = Modifier
                            .weight(DefaultWeight)
                            .padding(start = PaddingMedium),
                        shimmerOffset = shimmerOffset
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    ShimmerFavoriteCard(
                        modifier = Modifier
                            .weight(DefaultWeight)
                            .padding(end = PaddingMedium),
                        shimmerOffset = shimmerOffset
                    )
                }
            }
            Spacer(modifier = Modifier.height(PaddingExtraSmall))
        }
    }
}