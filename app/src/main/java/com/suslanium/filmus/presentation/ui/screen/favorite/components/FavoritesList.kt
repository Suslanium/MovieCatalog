package com.suslanium.filmus.presentation.ui.screen.favorite.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight

fun LazyListScope.favoritesList(
    moviesList: List<MovieSummary>,
    startOffsetX: Float
) {
    items(count = (moviesList.size / 3) * 2 + if (moviesList.size % 3 == 0) 0 else 1) { rowIndex ->
        val baseIndex = ((rowIndex + 1) / 2) * 3
        if ((rowIndex + 1) % 2 == 0) {
            val itemIndex = baseIndex - 1
            FavoriteMovieCard(
                modifier = Modifier.fillMaxWidth(),
                movieSummary = moviesList[itemIndex],
                shimmerOffset = startOffsetX
            )
        } else {
            val firstItemIndex = baseIndex
            val secondItemIndex = baseIndex + 1
            Row(modifier = Modifier.fillMaxWidth()) {
                FavoriteMovieCard(
                    modifier = Modifier.weight(DefaultWeight),
                    movieSummary = moviesList[firstItemIndex],
                    shimmerOffset = startOffsetX
                )
                Spacer(modifier = Modifier.width(15.dp))
                if (secondItemIndex < moviesList.size) {
                    FavoriteMovieCard(
                        modifier = Modifier.weight(DefaultWeight),
                        movieSummary = moviesList[secondItemIndex],
                        shimmerOffset = startOffsetX
                    )
                } else {
                    Box(modifier = Modifier.weight(DefaultWeight))
                }
            }
        }
    }
}