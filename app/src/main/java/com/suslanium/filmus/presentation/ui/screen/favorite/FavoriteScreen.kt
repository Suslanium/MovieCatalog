package com.suslanium.filmus.presentation.ui.screen.favorite

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.state.FavoritesListState
import com.suslanium.filmus.presentation.ui.common.ErrorContent
import com.suslanium.filmus.presentation.ui.screen.favorite.components.FavoritesShimmerList
import com.suslanium.filmus.presentation.ui.screen.favorite.components.favoritesList
import com.suslanium.filmus.presentation.ui.screen.favorite.components.noFavoritesPlaceHolder
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.viewmodel.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen() {
    val favoriteViewModel: FavoriteViewModel = koinViewModel()
    val favoritesListState by remember { favoriteViewModel.favoritesListState }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2.8f,
        targetValue = 2.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    Crossfade(targetState = favoritesListState, label = "") { state ->
        when (state) {
            is FavoritesListState.Content -> FavoritesList(state.movies, startOffsetX)
            FavoritesListState.Error -> ErrorContent(onRetry = favoriteViewModel::loadData)
            FavoritesListState.Loading -> FavoritesShimmerList(startOffsetX)
        }
    }
}

@Composable
private fun FavoritesList(
    moviesList: List<MovieSummary>,
    shimmerOffset: Float
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium),
        state = rememberLazyListState()
    ) {
        item {
            Spacer(modifier = Modifier.height(PaddingMedium))
        }
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.favorites),
                style = S24_W700,
                color = White,
                textAlign = TextAlign.Center
            )
        }
        if (moviesList.isNotEmpty()) {
            favoritesList(moviesList, shimmerOffset)
            item {
                Spacer(modifier = Modifier.height(PaddingMedium))
            }
        } else {
            noFavoritesPlaceHolder()
        }
    }
}