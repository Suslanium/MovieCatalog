package com.suslanium.filmus.presentation.ui.screen.main.components.moviecard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingNormal
import com.suslanium.filmus.presentation.ui.theme.RatingLessOrEqual2
import com.suslanium.filmus.presentation.ui.theme.RatingOver2
import com.suslanium.filmus.presentation.ui.theme.RatingOver3
import com.suslanium.filmus.presentation.ui.theme.RatingOver4
import com.suslanium.filmus.presentation.ui.theme.RatingOver6
import com.suslanium.filmus.presentation.ui.theme.RatingOver8

@Composable
fun MovieCard(
    movieSummary: MovieSummary, modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        MovieCardImage(movieSummary)
        Spacer(modifier = Modifier.width(PaddingNormal))
        Column {
            MovieCardNameAndRating(movieSummary)
            Spacer(modifier = Modifier.height(PaddingExtraSmall))
            MovieCardAdditionalInfo(movieSummary)
        }
    }
}


fun colorByRating(rating: Float): Color {
    return when {
        rating > 8 -> RatingOver8
        rating > 6 -> RatingOver6
        rating > 4 -> RatingOver4
        rating > 3 -> RatingOver3
        rating > 2 -> RatingOver2
        else -> RatingLessOrEqual2
    }
}