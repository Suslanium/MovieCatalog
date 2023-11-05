package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.CarouselIconSize
import com.suslanium.filmus.presentation.ui.theme.Gray750

@Composable
fun DetailsPoster(posterLink: Any?, startOffsetX: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(497.dp)
    ) {
        GlideImage(modifier = Modifier.fillMaxSize(),
            imageModel = { posterLink },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect(
                            startOffsetX = startOffsetX,
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
                        modifier = Modifier.size(CarouselIconSize),
                        imageVector = ImageVector.vectorResource(
                            R.drawable.download_error
                        ),
                        contentDescription = null,
                        tint = Accent
                    )
                }
            })
        Image(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            imageVector = ImageVector.vectorResource(R.drawable.image_mask),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}