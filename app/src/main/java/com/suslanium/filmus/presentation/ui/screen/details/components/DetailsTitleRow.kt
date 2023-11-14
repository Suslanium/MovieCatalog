package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.presentation.common.Constants.RATING_FORMAT
import com.suslanium.filmus.presentation.ui.common.colorByRating
import com.suslanium.filmus.presentation.ui.common.textColorByRating
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S15_W500
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun DetailsTitleRow(
    rating: Float,
    movieName: String,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean,
    lazyListStateProvider: () -> LazyListState
) {
    Spacer(
        modifier = Modifier
            .height(PaddingMedium)
            .fillMaxWidth()
            .background(Background)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(horizontal = PaddingMedium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(26.dp)
                .width(51.dp)
                .clip(
                    RoundedCornerShape(
                        MovieCardCornerRadiusMedium
                    )
                )
                .background(
                    colorByRating(rating)
                )
                .padding(vertical = PaddingExtraSmall, horizontal = PaddingSmall),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = String.format(RATING_FORMAT, rating),
                textAlign = TextAlign.Center,
                style = S15_W500,
                color = textColorByRating(rating)
            )
        }
        Text(
            modifier = Modifier
                .weight(DefaultWeight)
                .graphicsLayer {
                    if (lazyListStateProvider().firstVisibleItemIndex < 1) {
                        alpha = 1f
                    } else if (lazyListStateProvider().firstVisibleItemIndex == 1) {
                        alpha = 1f - lazyListStateProvider().firstVisibleItemScrollOffset * 0.008f
                    }
                },
            text = movieName,
            textAlign = TextAlign.Center,
            style = S24_W700,
            color = White
        )
        FavoriteButton(modifier = Modifier.graphicsLayer {
            if (lazyListStateProvider().firstVisibleItemIndex < 1) {
                alpha = 1f
            } else if (lazyListStateProvider().firstVisibleItemIndex == 1) {
                alpha = 1f - lazyListStateProvider().firstVisibleItemScrollOffset * 0.008f
            }
        }, changeFavoritesState = onFavoriteClick, isFavorite = isFavorite)
    }
}