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
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.S16_W700
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun MovieCardNameAndRating(
    movieSummary: MovieSummary
) {
    Row(verticalAlignment = Alignment.Top) {
        if (movieSummary.name != null) {
            Text(
                modifier = Modifier.weight(DefaultWeight),
                text = movieSummary.name,
                style = S16_W700,
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

