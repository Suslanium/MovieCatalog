package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.domain.entity.movie.MovieDetails
import com.suslanium.filmus.presentation.ui.screen.details.components.aboutsection.DetailsAboutSection
import com.suslanium.filmus.presentation.ui.screen.details.components.reviewelement.ReviewElement
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import java.time.format.DateTimeFormatter

@Composable
fun DetailsContent(
    onFavoriteClick: () -> Unit,
    dateTimeFormatter: DateTimeFormatter,
    paddingValues: PaddingValues,
    startOffsetXProvider: () -> Float,
    movieDetailsProvider: () -> MovieDetails,
    blurRadiusProvider: () -> Dp,
    onEditReviewClick: () -> Unit,
    onDeleteReviewClick: () -> Unit,
    onAddReviewClick: () -> Unit,
    isDeletingUserReview: () -> Boolean,
    lazyListStateProvider: () -> LazyListState
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(if (blurRadiusProvider() != 0.dp) Modifier.blur(blurRadiusProvider()) else Modifier),
        state = lazyListStateProvider(),
        contentPadding = paddingValues
    ) {
        //Poster
        item {
            DetailsPoster(
                { movieDetailsProvider().posterUri },
                { startOffsetXProvider() },
                { lazyListStateProvider().firstVisibleItemScrollOffset })
        }
        //Title, rating and fav button
        item {
            DetailsTitleRow(
                rating = movieDetailsProvider().rating ?: 0f,
                movieName = movieDetailsProvider().name.orEmpty(),
                onFavoriteClick = onFavoriteClick,
                isFavorite = movieDetailsProvider().isFavorite,
                lazyListStateProvider = lazyListStateProvider
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
        items(
            count = movieDetailsProvider().reviews.size,
            key = { movieDetailsProvider().reviews[it].id }) {
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