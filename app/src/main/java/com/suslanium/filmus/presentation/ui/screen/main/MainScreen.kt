package com.suslanium.filmus.presentation.ui.screen.main

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.common.Constants
import com.suslanium.filmus.presentation.common.Constants.MODIFIED_FILM_ID
import com.suslanium.filmus.presentation.common.Constants.NEW_RATING
import com.suslanium.filmus.presentation.common.Constants.NEW_USER_RATING
import com.suslanium.filmus.presentation.ui.common.ErrorContent
import com.suslanium.filmus.presentation.ui.common.shimmerOffsetAnimation
import com.suslanium.filmus.presentation.ui.screen.main.components.MovieList
import com.suslanium.filmus.presentation.ui.screen.main.components.MovieShimmerList
import com.suslanium.filmus.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun MainScreen(
    navController: NavController
) {
    val mainViewModel: MainViewModel = koinViewModel()
    val moviePagingItems: LazyPagingItems<MovieSummary> =
        mainViewModel.movies.collectAsLazyPagingItems()
    val modifiedMovies = remember { mainViewModel.modifiedMovies }
    val transition = rememberInfiniteTransition(label = Constants.EMPTY_STRING)
    val startOffsetX by shimmerOffsetAnimation(transition)

    val modifiedMovieState =
        navController.currentBackStackEntry?.savedStateHandle?.getStateFlow<String?>(
            MODIFIED_FILM_ID, null
        )?.collectAsStateWithLifecycle()
    LaunchedEffect(modifiedMovieState?.value) {
        val movieId = modifiedMovieState?.value
        if (movieId != null) {
            mainViewModel.setModifiedMovie(
                UUID.fromString(movieId),
                navController.currentBackStackEntry?.savedStateHandle?.get(NEW_RATING),
                navController.currentBackStackEntry?.savedStateHandle?.get(NEW_USER_RATING)
            )
            navController.currentBackStackEntry?.savedStateHandle?.set(MODIFIED_FILM_ID, null)
        }
    }

    Crossfade(targetState = moviePagingItems.loadState.refresh, label = Constants.EMPTY_STRING) {
        when (it) {
            is LoadState.NotLoading -> {
                MovieList(navController, modifiedMovies, moviePagingItems) { startOffsetX }
            }

            is LoadState.Loading -> {
                MovieShimmerList { startOffsetX }
            }

            is LoadState.Error -> {
                ErrorContent(onRetry = {
                    moviePagingItems.refresh()
                    mainViewModel.clearModifiedMovies()
                })
            }
        }
    }

}

