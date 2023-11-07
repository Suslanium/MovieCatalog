package com.suslanium.filmus.presentation.ui.screen.details

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import com.suslanium.filmus.presentation.state.DetailsState
import com.suslanium.filmus.presentation.state.ReviewState
import com.suslanium.filmus.presentation.ui.common.ErrorContent
import com.suslanium.filmus.presentation.ui.screen.details.components.DetailsExpandableDescription
import com.suslanium.filmus.presentation.ui.screen.details.components.DetailsGenreList
import com.suslanium.filmus.presentation.ui.screen.details.components.DetailsPoster
import com.suslanium.filmus.presentation.ui.screen.details.components.DetailsReviewHeader
import com.suslanium.filmus.presentation.ui.screen.details.components.DetailsTitleRow
import com.suslanium.filmus.presentation.ui.screen.details.components.aboutsection.DetailsAboutSection
import com.suslanium.filmus.presentation.ui.screen.details.components.reviewdialog.ReviewDialog
import com.suslanium.filmus.presentation.ui.screen.details.components.reviewelement.ReviewElement
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.format.DateTimeFormatter
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(movieId: UUID, navController: NavController) {
    val detailsViewModel: DetailsViewModel = koinViewModel { parametersOf(movieId) }
    val detailsState by remember { detailsViewModel.detailsState }
    val detailsData by remember { detailsViewModel.detailsData }
    val reviewState by remember { detailsViewModel.reviewState }
    val reviewData by remember { detailsViewModel.reviewData }
    val canSaveReview by remember { detailsViewModel.canSaveReview }
    val canSetReviewAnonymous by remember { detailsViewModel.reviewIsAnonymousAvailable }

    val blurRadius by animateDpAsState(
        targetValue = if (reviewState != ReviewState.DialogClosed && reviewState != ReviewState.Deleting) 3.dp else 0.dp,
        label = ""
    )

    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2.8f, targetValue = 2.8f, animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    Scaffold(topBar = {
        CenterAlignedTopAppBar(modifier = Modifier
            .statusBarsPadding()
            .height(44.dp)
            .fillMaxWidth(),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Background,
                navigationIconContentColor = White,
                titleContentColor = White
            ),
            title = { /*TODO*/ },
            navigationIcon = {
                IconButton(onClick = navController::navigateUp) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.back_icon),
                        contentDescription = null,
                        tint = White
                    )
                }
            })
    }) { paddingValues ->
        Crossfade(targetState = detailsState, label = "") {
            when(it) {
                DetailsState.Content -> DetailsContent(
                    detailsViewModel::changeFavoritesState,
                    detailsViewModel.dateFormat,
                    paddingValues,
                    { startOffsetX },
                    { detailsData },
                    { blurRadius },
                    detailsViewModel::openReviewDialog,
                    detailsViewModel::deleteReview,
                    detailsViewModel::openReviewDialog,
                    { reviewState == ReviewState.Deleting }
                )
                DetailsState.Error -> ErrorContent(onRetry = detailsViewModel::loadFilmData)
                DetailsState.Loading -> Unit
            }
        }

    }

    if (reviewState != ReviewState.DialogClosed && reviewState != ReviewState.Deleting) {
        ReviewDialog(
            dismissDialog = detailsViewModel::closeReviewDialog,
            saveReview = detailsViewModel::saveReview,
            reviewTextProvider = { reviewData.reviewText },
            setReviewText = detailsViewModel::setReviewText,
            ratingProvider = { reviewData.rating },
            setRating = detailsViewModel::setReviewRating,
            isAnonymousProvider = { reviewData.isAnonymous },
            setAnonymous = detailsViewModel::setReviewIsAnonymous,
            anonymousCheckboxEnabled = canSetReviewAnonymous,
            saveButtonEnabled = { canSaveReview },
            dialogState = { reviewState }
        )
    }
}

@Composable
private fun DetailsContent(
    onFavoriteClick: () -> Unit,
    dateTimeFormatter: DateTimeFormatter,
    paddingValues: PaddingValues,
    startOffsetXProvider: () -> Float,
    movieDetailsProvider: () -> MovieDetails,
    blurRadiusProvider: () -> Dp,
    onEditReviewClick: () -> Unit,
    onDeleteReviewClick: () -> Unit,
    onAddReviewClick: () -> Unit,
    isDeletingUserReview: () -> Boolean
) {

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(if (blurRadiusProvider() != 0.dp) Modifier.blur(blurRadiusProvider()) else Modifier),
        state = lazyListState,
        contentPadding = paddingValues
    ) {
        //Poster
        item {
            DetailsPoster(
                { movieDetailsProvider().posterUri },
                { startOffsetXProvider() },
                { lazyListState.firstVisibleItemScrollOffset })
        }
        //Title, rating and fav button
        item {
            DetailsTitleRow(
                rating = movieDetailsProvider().rating ?: 0f,
                movieName = movieDetailsProvider().name.orEmpty(),
                onFavoriteClick = onFavoriteClick,
                isFavorite = movieDetailsProvider().isFavorite
            )
        }
        //Expandable desc
        item {
            DetailsExpandableDescription(description = movieDetailsProvider().description.orEmpty())
        }
        //Genres
        item {
            DetailsGenreList(movieDetailsProvider().genres)
        }
        //About section
        item {
            DetailsAboutSection(
                year = movieDetailsProvider().year,
                country = movieDetailsProvider().country,
                tagLine = movieDetailsProvider().tagLine,
                director = movieDetailsProvider().director,
                budget = movieDetailsProvider().budget,
                fees = movieDetailsProvider().fees,
                minimalAge = movieDetailsProvider().minimalAge,
                lengthMinutes = movieDetailsProvider().lengthMinutes
            )
        }
        //Reviews header
        item {
            DetailsReviewHeader(
                userHasNoReview = movieDetailsProvider().userReview == null,
                onAddReview = onAddReviewClick
            )
        }
        items(count = movieDetailsProvider().reviews.size, key = { movieDetailsProvider().reviews[it].id }) {
            ReviewElement(
                review = movieDetailsProvider().reviews[it],
                shimmerOffsetProvider = { startOffsetXProvider() },
                dateFormat = dateTimeFormatter,
                isUserReview = movieDetailsProvider().reviews[it].id == movieDetailsProvider().userReview?.id,
                onEditUserReview = onEditReviewClick,
                onRemoveUserReview = onDeleteReviewClick,
                isDeletingReview = isDeletingUserReview
            )
            Spacer(modifier = Modifier.height(PaddingLarge))
        }
    }
}