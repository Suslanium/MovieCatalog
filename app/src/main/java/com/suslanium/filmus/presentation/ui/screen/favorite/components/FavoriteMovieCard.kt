package com.suslanium.filmus.presentation.ui.screen.favorite.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.common.UserRating
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingUltraSmall
import com.suslanium.filmus.presentation.ui.theme.S14_W500
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun FavoriteMovieCard(
    modifier: Modifier = Modifier, movieSummary: MovieSummary, shimmerOffset: Float
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(205.dp), contentAlignment = Alignment.Center
        ) {
            GlideImage(modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(MovieCardCornerRadiusSmall)
                ),
                imageModel = { movieSummary.posterUri },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmerEffect(
                                startOffsetX = shimmerOffset,
                                backgroundColor = Gray750,
                                shimmerColor = Accent
                            )
                    )
                },
                failure = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Gray750),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier, imageVector = ImageVector.vectorResource(
                                R.drawable.download_error
                            ), contentDescription = null, tint = Accent
                        )
                    }
                })

            if (movieSummary.userRating != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = PaddingUltraSmall),
                    contentAlignment = Alignment.TopEnd
                ) {
                    UserRating(userRating = movieSummary.userRating)
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = movieSummary.name.orEmpty(),
            color = White,
            style = S14_W500,
            textAlign = TextAlign.Start
        )
    }
}