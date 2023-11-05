package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.Genre
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.GenreDetailsElementPadding
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S15_W500
import com.suslanium.filmus.presentation.ui.theme.S16_W700
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun DetailsGenreList(genresList: List<Genre>) {
    Spacer(modifier = Modifier.height(PaddingLarge))
    Text(
        modifier = Modifier.padding(horizontal = PaddingMedium), text = stringResource(
            id = R.string.genres
        ), style = S16_W700, color = White
    )
    Spacer(modifier = Modifier.height(PaddingLarge / 2))
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        horizontalArrangement = Arrangement.spacedBy(PaddingSmall),
        verticalArrangement = Arrangement.spacedBy(PaddingSmall)
    ) {
        repeat(genresList.size) { genreIndex ->
            val name = genresList[genreIndex].name
            if (name != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(MovieCardCornerRadiusMedium))
                        .background(Accent)
                        .padding(GenreDetailsElementPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name,
                        style = S15_W500,
                        color = White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}