package com.suslanium.filmus.presentation.ui.screen.details

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.suslanium.filmus.presentation.state.DetailsState
import com.suslanium.filmus.presentation.state.LogoutEvent
import com.suslanium.filmus.presentation.state.ReviewState
import com.suslanium.filmus.presentation.ui.common.ErrorContent
import com.suslanium.filmus.presentation.ui.common.ObserveAsEvents
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.screen.details.components.DetailsContent
import com.suslanium.filmus.presentation.ui.screen.details.components.DetailsShimmerContent
import com.suslanium.filmus.presentation.ui.screen.details.components.DetailsTopBar
import com.suslanium.filmus.presentation.ui.screen.details.components.reviewdialog.ReviewDialog
import com.suslanium.filmus.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.util.UUID

@Composable
fun DetailsScreen(movieId: UUID, navController: NavController) {
    val detailsViewModel: DetailsViewModel = koinViewModel { parametersOf(movieId) }
    val detailsState by remember { detailsViewModel.detailsState }
    val detailsData by remember { detailsViewModel.detailsData }
    val reviewState by remember { detailsViewModel.reviewState }
    val reviewData by remember { detailsViewModel.reviewData }
    val canSaveReview by remember { detailsViewModel.canSaveReview }
    val canSetReviewAnonymous by remember { detailsViewModel.reviewIsAnonymousAvailable }

    LaunchedEffect(detailsData.isFavorite, detailsData.rating, detailsData.userReview) {
        navController.previousBackStackEntry?.savedStateHandle?.set(
            "modifiedFilmId",
            detailsData.id.toString()
        )
        navController.previousBackStackEntry?.savedStateHandle?.set(
            "isFavorite",
            detailsData.isFavorite
        )
        navController.previousBackStackEntry?.savedStateHandle?.set("rating", detailsData.rating)
        navController.previousBackStackEntry?.savedStateHandle?.set(
            "userRating",
            detailsData.userReview?.rating
        )
    }

    ObserveAsEvents(flow = detailsViewModel.logoutEvents) {
        when (it) {
            LogoutEvent.Logout -> navController.navigate(FilmusDestinations.ONBOARDING)
        }
    }

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


    val lazyListState = rememberLazyListState()

    Scaffold(topBar = {
        DetailsTopBar(
            { lazyListState },
            { detailsData },
            detailsViewModel,
            navController
        )
    }
    ) { paddingValues ->
        Crossfade(targetState = detailsState, label = "") {
            when (it) {
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
                    { reviewState == ReviewState.Deleting },
                    { lazyListState }
                )

                DetailsState.Error -> ErrorContent(onRetry = detailsViewModel::loadFilmData)
                DetailsState.Loading -> DetailsShimmerContent(
                    paddingValues = paddingValues
                ) { startOffsetX }
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

