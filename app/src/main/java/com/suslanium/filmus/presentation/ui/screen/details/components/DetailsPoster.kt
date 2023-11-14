package com.suslanium.filmus.presentation.ui.screen.details.components

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.CarouselIconSize
import com.suslanium.filmus.presentation.ui.theme.Gray750

val detailsGradientColorStops = arrayOf(
    0.66f to Color(0x001D1D1D),
    0.88f to Color(0xFF1D1D1D)
)

@Composable
fun DetailsPoster(
    posterLinkProvider: () -> Any?,
    startOffsetXProvider: () -> Float,
    firstVisibleItemOffsetProvider: () -> Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(497.dp)
    ) {
        GlideImage(modifier = Modifier.graphicsLayer {
            translationY = firstVisibleItemOffsetProvider() * 0.5f
            scaleX = 1f + firstVisibleItemOffsetProvider() * 0.0003f
            scaleY = 1f + firstVisibleItemOffsetProvider() * 0.0003f
            alpha = 1f - firstVisibleItemOffsetProvider() * 0.001f
        }.fillMaxSize(),
            imageModel = posterLinkProvider,
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect(
                            startOffsetXProvider = startOffsetXProvider,
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colorStops = detailsGradientColorStops))
        )
    }
}