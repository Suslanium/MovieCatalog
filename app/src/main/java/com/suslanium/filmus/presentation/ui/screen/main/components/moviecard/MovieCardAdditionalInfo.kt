package com.suslanium.filmus.presentation.ui.screen.main.components.moviecard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.theme.GenreElementPadding
import com.suslanium.filmus.presentation.ui.theme.S13_W400
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.S12_W400
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingNormal
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun MovieCardAdditionalInfo(movieSummary: MovieSummary) {
    Text(
        text = if (movieSummary.country == null) movieSummary.year.toString()
        else "${movieSummary.year} · ${movieSummary.country}",
        style = S12_W400,
        color = White,
        maxLines = 1
    )
    Spacer(modifier = Modifier.height(PaddingNormal))
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(PaddingExtraSmall),
        verticalArrangement = Arrangement.spacedBy(PaddingExtraSmall)
    ) {
        repeat(movieSummary.genres.size) { genreIndex ->
            val name = movieSummary.genres[genreIndex].name
            if (name != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(MovieCardCornerRadiusMedium))
                        .background(Gray750)
                        .padding(GenreElementPadding), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name,
                        style = S13_W400,
                        color = White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}