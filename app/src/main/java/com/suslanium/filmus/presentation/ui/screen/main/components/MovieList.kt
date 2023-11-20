package com.suslanium.filmus.presentation.ui.screen.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.state.ModifiedMovie
import com.suslanium.filmus.presentation.ui.common.NoRippleInteractionSource
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.screen.main.components.moviecard.MovieCard
import com.suslanium.filmus.presentation.ui.screen.main.components.moviecard.ShimmerMovieCard
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.S15_W500
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.White
import java.util.UUID

@Composable
fun MovieList(
    navController: NavController,
    modifiedMovies: SnapshotStateMap<UUID, ModifiedMovie>,
    moviePagingItems: LazyPagingItems<MovieSummary>,
    shimmerOffsetProvider: () -> Float
) {
    val movieList = remember {
        val movieList = mutableListOf<MovieSummary>()
        repeat(4) { index ->
            moviePagingItems[index]?.let { movieList.add(it) }
        }
        movieList
    }
    LazyColumn(modifier = Modifier.fillMaxSize(), state = rememberLazyListState()) {

        item {
            PosterCarousel(
                movies = movieList,
                shimmerOffsetProvider = shimmerOffsetProvider,
                navController = navController
            )
        }

        item {
            Box(
                modifier = Modifier.padding(
                    start = PaddingMedium, end = PaddingMedium, top = PaddingMedium, bottom = 15.dp
                ), contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = stringResource(id = R.string.catalog),
                    style = S24_W700,
                    color = White,
                    textAlign = TextAlign.Start
                )
            }
        }

        items(count = moviePagingItems.itemCount - 4, key = { moviePagingItems[it]?.id ?: it }) {
            var movie = moviePagingItems[it + 4]
            if (movie != null) {
                if (modifiedMovies.containsKey(movie.id)) movie = movie.copy(
                    rating = modifiedMovies[movie.id]?.newRating,
                    userRating = modifiedMovies[movie.id]?.newUserRating
                )
                MovieCard(
                    movieSummary = movie,
                    modifier = Modifier
                        .padding(
                            start = PaddingMedium, end = PaddingMedium, bottom = PaddingMedium
                        )
                        .clickable(interactionSource = NoRippleInteractionSource(),
                            indication = null,
                            onClick = { navController.navigate("${FilmusDestinations.DETAILS_NO_ID}/${movie.id}") }),
                    shimmerOffsetProvider = shimmerOffsetProvider
                )
            }
        }

        when (moviePagingItems.loadState.append) {
            is LoadState.Error -> item {
                ErrorItem(moviePagingItems::retry)
            }

            LoadState.Loading -> item {
                ShimmerMovieCard(
                    modifier = Modifier.padding(
                        start = PaddingMedium, end = PaddingMedium, bottom = PaddingMedium
                    ), shimmerOffsetProvider = shimmerOffsetProvider
                )
            }

            is LoadState.NotLoading -> Unit
        }
    }
}

@Composable
private fun ErrorItem(onRetry: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(
                start = PaddingMedium, end = PaddingMedium, bottom = PaddingMedium
            )
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.unknown_error),
            style = S15_W500,
            color = White,
            textAlign = TextAlign.Start
        )
        Icon(
            modifier = Modifier
                .clickable(onClick = onRetry)
                .size(20.dp),
            imageVector = ImageVector.vectorResource(R.drawable.download_error),
            contentDescription = null,
            tint = Accent
        )
    }
}