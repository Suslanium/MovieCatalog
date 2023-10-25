package com.suslanium.filmus.presentation.ui.screen.main.components.moviecard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusLarge
import com.suslanium.filmus.presentation.ui.theme.MovieCardNameAndRatingHeight
import com.suslanium.filmus.presentation.ui.theme.MovieCardTitleText
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.UserRatingText
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
            Row(
                modifier = Modifier
                    .height(MovieCardNameAndRatingHeight)
                    .clip(
                        RoundedCornerShape(MovieCardCornerRadiusLarge)
                    )
                    .background(
                        colorByRating(movieSummary.userRating.toFloat())
                    )
                    .padding(
                        top = PaddingExtraSmall,
                        bottom = PaddingExtraSmall,
                        end = PaddingExtraSmall,
                        start = 6.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.star_icon),
                    contentDescription = null,
                    tint = White
                )
                Spacer(modifier = Modifier.width(PaddingExtraSmall))
                Text(
                    text = movieSummary.userRating.toString(),
                    style = UserRatingText,
                    color = White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}