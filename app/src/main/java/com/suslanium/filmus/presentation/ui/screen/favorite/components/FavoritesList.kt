package com.suslanium.filmus.presentation.ui.screen.favorite.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge

fun LazyListScope.favoritesList(
    moviesList: List<MovieSummary>,
    shimmerOffsetProvider: () -> Float,
    navController: NavController
) {
    items(count = (moviesList.size / 3) * 2 + if (moviesList.size % 3 == 0) 0 else 1) { rowIndex ->
        Spacer(modifier = Modifier.height(PaddingLarge))
        val baseIndex = ((rowIndex + 1) / 2) * 3
        if ((rowIndex + 1) % 2 == 0) {
            val itemIndex = baseIndex - 1
            FavoriteMovieCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("${FilmusDestinations.DETAILS_NO_ID}/${moviesList[itemIndex].id}") },
                movieSummary = moviesList[itemIndex],
                shimmerOffsetProvider = shimmerOffsetProvider
            )
        } else {
            val firstItemIndex = baseIndex
            val secondItemIndex = baseIndex + 1
            Row(modifier = Modifier.fillMaxWidth()) {
                FavoriteMovieCard(
                    modifier = Modifier
                        .weight(DefaultWeight)
                        .clickable { navController.navigate("${FilmusDestinations.DETAILS_NO_ID}/${moviesList[firstItemIndex].id}") },
                    movieSummary = moviesList[firstItemIndex],
                    shimmerOffsetProvider = shimmerOffsetProvider
                )
                Spacer(modifier = Modifier.width(15.dp))
                if (secondItemIndex < moviesList.size) {
                    FavoriteMovieCard(
                        modifier = Modifier
                            .weight(DefaultWeight)
                            .clickable { navController.navigate("${FilmusDestinations.DETAILS_NO_ID}/${moviesList[secondItemIndex].id}") },
                        movieSummary = moviesList[secondItemIndex],
                        shimmerOffsetProvider = shimmerOffsetProvider
                    )
                } else {
                    Box(modifier = Modifier.weight(DefaultWeight))
                }
            }
        }
    }
}