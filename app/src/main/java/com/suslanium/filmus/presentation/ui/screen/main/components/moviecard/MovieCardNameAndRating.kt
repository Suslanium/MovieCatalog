package com.suslanium.filmus.presentation.ui.screen.main.components.moviecard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.common.UserRating
import com.suslanium.filmus.presentation.ui.theme.MovieCardTitleText
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun MovieCardNameAndRating(
    movieSummary: MovieSummary
) {
    Row(verticalAlignment = Alignment.Top) {
        if (movieSummary.name != null) {
            Text(
                modifier = Modifier.weight(1f),
                text = movieSummary.name,
                style = MovieCardTitleText,
                color = White,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.requiredWidth(10.dp))
        if (movieSummary.userRating != null) {
            UserRating(movieSummary.userRating)
        }
    }
}

