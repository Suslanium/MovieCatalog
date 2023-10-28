package com.suslanium.filmus.presentation.ui.screen.main.components.moviecard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingNormal

@Composable
fun MovieCard(
    movieSummary: MovieSummary, modifier: Modifier = Modifier, shimmerOffset: Float
) {
    Row(modifier = modifier.fillMaxWidth()) {
        MovieCardImage(movieSummary, shimmerOffset)
        Spacer(modifier = Modifier.width(PaddingNormal))
        Column {
            MovieCardNameAndRating(movieSummary)
            Spacer(modifier = Modifier.height(PaddingExtraSmall))
            MovieCardAdditionalInfo(movieSummary)
        }
    }
}