package com.suslanium.filmus.presentation.ui.screen.favorite

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.common.Constants
import com.suslanium.filmus.presentation.common.Constants.IS_FAVORITE
import com.suslanium.filmus.presentation.common.Constants.MODIFIED_FILM_ID
import com.suslanium.filmus.presentation.common.Constants.NEW_USER_RATING
import com.suslanium.filmus.presentation.state.FavoritesListState
import com.suslanium.filmus.presentation.state.LogoutEvent
import com.suslanium.filmus.presentation.ui.common.ErrorContent
import com.suslanium.filmus.presentation.ui.common.ObserveAsEvents
import com.suslanium.filmus.presentation.ui.common.shimmerOffsetAnimation
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.screen.favorite.components.FavoritesShimmerList
import com.suslanium.filmus.presentation.ui.screen.favorite.components.favoritesList
import com.suslanium.filmus.presentation.ui.screen.favorite.components.noFavoritesPlaceHolder
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.viewmodel.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun FavoriteScreen(
    navController: NavController
) {
    val favoriteViewModel: FavoriteViewModel = koinViewModel()
    val favoritesListState by remember { favoriteViewModel.favoritesListState }
    val transition = rememberInfiniteTransition(label = Constants.EMPTY_STRING)
    val startOffsetX by shimmerOffsetAnimation(transition)

    val modifiedMovieState =
        navController.currentBackStackEntry?.savedStateHandle?.getStateFlow<String?>(
            MODIFIED_FILM_ID, null
        )?.collectAsStateWithLifecycle()
    LaunchedEffect(modifiedMovieState?.value) {
        val movieId = modifiedMovieState?.value
        if (movieId != null) {
            val isFavorite =
                navController.currentBackStackEntry?.savedStateHandle?.get<Boolean?>(IS_FAVORITE)
            if (isFavorite != true) favoriteViewModel.removeMovie(UUID.fromString(movieId))
            else favoriteViewModel.modifyMovie(
                UUID.fromString(movieId),
                navController.currentBackStackEntry?.savedStateHandle?.get(NEW_USER_RATING)
            )
            navController.currentBackStackEntry?.savedStateHandle?.set(MODIFIED_FILM_ID, null)
        }
    }

    ObserveAsEvents(flow = favoriteViewModel.logoutEvents) {
        when (it) {
            LogoutEvent.Logout -> navController.navigate(FilmusDestinations.ONBOARDING) {
                popUpTo(FilmusDestinations.MAIN) {
                    inclusive = true
                }
            }
        }
    }

    Crossfade(targetState = favoritesListState, label = Constants.EMPTY_STRING) { state ->
        when (state) {
            is FavoritesListState.Content -> FavoritesList(state.movies,
                { startOffsetX }, navController
            )

            FavoritesListState.Error -> ErrorContent(onRetry = favoriteViewModel::loadData)
            FavoritesListState.Loading -> FavoritesShimmerList { startOffsetX }
        }
    }
}

@Composable
private fun FavoritesList(
    moviesList: List<MovieSummary>,
    shimmerOffset: () -> Float,
    navController: NavController
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
            favoritesList(moviesList, shimmerOffset, navController)
            item {
                Spacer(modifier = Modifier.height(PaddingMedium))
            }
        } else {
            noFavoritesPlaceHolder()
        }
    }
}