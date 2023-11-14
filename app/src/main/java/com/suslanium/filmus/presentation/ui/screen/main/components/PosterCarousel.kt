package com.suslanium.filmus.presentation.ui.screen.main.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.CarouselIconSize
import com.suslanium.filmus.presentation.ui.theme.CarouselIndicatorCornerRadius
import com.suslanium.filmus.presentation.ui.theme.CarouselPadding
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MoviePosterCarouselHeight
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PosterCarousel(
    movies: List<MovieSummary>,
    shimmerOffsetProvider: () -> Float,
    navController: NavController
) {
    val pagerState = rememberPagerState(pageCount = { movies.size })

    AutoScroll(pagerState)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MoviePosterCarouselHeight)
            .background(Gray750), contentAlignment = Alignment.Center
    ) {
        HorizontalPager(state = pagerState) { pageIndex ->
            GlideImage(
                modifier = Modifier.fillMaxSize().clickable {
                    navController.navigate("${FilmusDestinations.DETAILS_NO_ID}/${movies[pageIndex].id}")
                },
                imageModel = { movies[pageIndex].posterUri },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmerEffect(startOffsetXProvider = shimmerOffsetProvider, backgroundColor = Gray750, shimmerColor = Accent)
                    )
                },
                failure = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            modifier = Modifier.size(CarouselIconSize),
                            imageVector = ImageVector.vectorResource(R.drawable.download_error),
                            contentDescription = null,
                            tint = Accent
                        )
                    }
                }
            )
        }
        PageIndicator(movies, pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PageIndicator(
    movies: List<MovieSummary>,
    pagerState: PagerState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = CarouselPadding),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color(0x1AFFFFFF), shape = RoundedCornerShape(
                        CarouselIndicatorCornerRadius
                    )
                )
                .padding(all = PaddingSmall),
            horizontalArrangement = Arrangement.spacedBy(PaddingSmall)
        ) {
            repeat(movies.size) { index ->
                val drawableId =
                    if (pagerState.currentPage == index) R.drawable.selected_dot else R.drawable.inactive_dot
                Icon(
                    imageVector = ImageVector.vectorResource(drawableId),
                    contentDescription = null
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AutoScroll(
    pagerState: PagerState
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (!isDragged) {
        LaunchedEffect(Unit) {
            var scrollForward = true
            while (true) {
                delay(7000)
                with(pagerState) {
                    val target: Int
                    if (scrollForward && currentPage < pageCount - 1) target = currentPage + 1
                    else if (scrollForward) {
                        target = currentPage - 1
                        scrollForward = false
                    } else if (currentPage > 0) {
                        target = currentPage - 1
                    } else {
                        target = currentPage + 1
                        scrollForward = true
                    }

                    animateScrollToPage(
                        page = target,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
            }
        }
    }
}