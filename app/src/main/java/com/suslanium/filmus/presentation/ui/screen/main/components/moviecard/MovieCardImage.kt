package com.suslanium.filmus.presentation.ui.screen.main.components.moviecard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.suslanium.filmus.presentation.common.Constants.RATING_FORMAT
import com.suslanium.filmus.presentation.ui.common.colorByRating
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusSmall
import com.suslanium.filmus.presentation.ui.theme.MovieCardImageHeight
import com.suslanium.filmus.presentation.ui.theme.MovieCardImageWidth
import com.suslanium.filmus.presentation.ui.theme.S13_W700
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingUltraSmall

@Composable
fun MovieCardImage(movieSummary: MovieSummary, shimmerOffsetProvider: () -> Float) {
    Box(
        modifier = Modifier
            .height(MovieCardImageHeight)
            .width(MovieCardImageWidth), contentAlignment = Alignment.Center
    ) {

        GlideImage(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(MovieCardCornerRadiusSmall)),
            imageModel = { movieSummary.posterUri },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect(
                            startOffsetXProvider = shimmerOffsetProvider,
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

        if (movieSummary.rating != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = PaddingUltraSmall),
                contentAlignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .clip(RoundedCornerShape(MovieCardCornerRadiusMedium))
                        .background(
                            colorByRating(movieSummary.rating)
                        )
                        .padding(vertical = PaddingUltraSmall, horizontal = PaddingSmall),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = String.format(RATING_FORMAT, movieSummary.rating),
                        textAlign = TextAlign.Center,
                        style = S13_W700,
                        color = Background
                    )
                }
            }

        }
    }
}