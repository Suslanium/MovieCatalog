package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.viewmodel.DetailsViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailsTopBar(
    lazyListStateProvider: () -> LazyListState,
    detailsDataProvider: () -> MovieDetails,
    detailsViewModel: DetailsViewModel,
    navController: NavController
) {
    CenterAlignedTopAppBar(modifier = Modifier
        .statusBarsPadding()
        .height(44.dp)
        .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Background,
            navigationIconContentColor = White,
            titleContentColor = White
        ),
        title = {
            Text(
                modifier = Modifier
                    .padding(start = PaddingSmall, top = PaddingSmall)
                    .graphicsLayer {
                        if (lazyListStateProvider().firstVisibleItemIndex < 1) {
                            alpha = 0f
                        } else if (lazyListStateProvider().firstVisibleItemIndex == 1) {
                            alpha = lazyListStateProvider().firstVisibleItemScrollOffset * 0.008f
                        }
                    },
                text = detailsDataProvider().name.orEmpty(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = S24_W700,
                color = White
            )
        },
        actions = {
            FavoriteButton(Modifier.graphicsLayer {
                if (lazyListStateProvider().firstVisibleItemIndex < 1) {
                    alpha = 0f
                } else if (lazyListStateProvider().firstVisibleItemIndex == 1) {
                    alpha = lazyListStateProvider().firstVisibleItemScrollOffset * 0.008f
                }
            }, detailsViewModel::changeFavoritesState, detailsDataProvider().isFavorite)
            Spacer(modifier = Modifier.width(12.dp))
        },
        navigationIcon = {
            IconButton(onClick = { if (navController.previousBackStackEntry?.destination?.route == FilmusDestinations.MAIN) navController.navigateUp() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.back_icon),
                    contentDescription = null,
                    tint = White
                )
            }
        })
}