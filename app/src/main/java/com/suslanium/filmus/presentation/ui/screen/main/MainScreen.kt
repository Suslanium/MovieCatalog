package com.suslanium.filmus.presentation.ui.screen.main

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.common.ErrorContent
import com.suslanium.filmus.presentation.ui.screen.main.components.MovieList
import com.suslanium.filmus.presentation.ui.screen.main.components.MovieShimmerList
import com.suslanium.filmus.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavController
) {
    val mainViewModel: MainViewModel = koinViewModel()
    val moviePagingItems: LazyPagingItems<MovieSummary> =
        mainViewModel.movies.collectAsLazyPagingItems()
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2.8f,
        targetValue = 2.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )
    Crossfade(targetState = moviePagingItems.loadState.refresh, label = "") {
        when (it) {
            is LoadState.NotLoading -> {
                MovieList(navController, moviePagingItems, startOffsetX)
            }

            is LoadState.Loading -> {
                MovieShimmerList(startOffsetX)
            }

            is LoadState.Error -> {
                ErrorContent(onRetry = moviePagingItems::refresh)
            }
        }
    }

}

