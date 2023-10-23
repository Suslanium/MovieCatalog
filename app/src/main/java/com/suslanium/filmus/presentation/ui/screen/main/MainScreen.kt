package com.suslanium.filmus.presentation.ui.screen.main

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.screen.main.components.ErrorContent
import com.suslanium.filmus.presentation.ui.screen.main.components.MovieList
import com.suslanium.filmus.presentation.ui.screen.main.components.MovieShimmerList
import com.suslanium.filmus.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val mainViewModel: MainViewModel = koinViewModel()
    val moviePagingItems: LazyPagingItems<MovieSummary> =
        mainViewModel.movies.collectAsLazyPagingItems()
    when (moviePagingItems.loadState.refresh) {
        is LoadState.NotLoading -> {
            MovieList(moviePagingItems)
        }

        is LoadState.Loading -> {
            MovieShimmerList()
        }

        is LoadState.Error -> {
            ErrorContent(onRetry = moviePagingItems::refresh)
        }
    }
}

