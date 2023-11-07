package com.suslanium.filmus.presentation.ui.screen.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.Genre
import com.suslanium.filmus.domain.entity.movie.Review
import com.suslanium.filmus.domain.entity.user.UserSummary
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen() {
    var shouldShowReviewDialog by remember { mutableStateOf(false) }
    val blurRadius by animateDpAsState(
        targetValue = if (shouldShowReviewDialog) 3.dp else 0.dp,
        label = ""
    )
    val lazyListState = rememberLazyListState()

    val genresList = listOf(
        Genre(UUID.randomUUID(), "боевик"),
        Genre(UUID.randomUUID(), "фантастика"),
        Genre(UUID.randomUUID(), "драма"),
        Genre(UUID.randomUUID(), "мелодрама")
    )
    val reviewList = listOf(
        Review(
            id = UUID.randomUUID(),
            rating = 10,
            reviewText = "Бэнгер",
            isAnonymous = false,
            creationDateTime = LocalDateTime.now(),
            author = UserSummary(
                userId = UUID.randomUUID(),
                nickName = "Death",
                avatar = "https://media.tenor.com/XFm6btzScZUAAAAC/death-puss-in-boots.gif"
            )
        ),
        Review(
            id = UUID.randomUUID(),
            rating = 10,
            reviewText = "НУ И ДЕЛА ДРУГАЛЁК",
            isAnonymous = false,
            creationDateTime = LocalDateTime.now(),
            author = UserSummary(
                userId = UUID.randomUUID(),
                nickName = "Komaru cat",
                avatar = "https://media.tenor.com/lStgaNc7YHAAAAAC/komaru-cat.gif"
            )
        ),
        Review(
            id = UUID.randomUUID(),
            rating = 10,
            reviewText = "Бэнгер",
            isAnonymous = true,
            creationDateTime = LocalDateTime.now(),
            author = null
        ),
        Review(
            id = UUID.randomUUID(),
            rating = 7,
            reviewText = "Ьаовьаовьоаьоваоаьоаььовьвоьаоьвоььовоьаоьваьооьваьовоь",
            isAnonymous = true,
            creationDateTime = LocalDateTime.now(),
            author = null
        )
    )
    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

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
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.back_icon),
                        contentDescription = null,
                        tint = White
                    )
                }
            })
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .then(if (blurRadius != 0.dp) Modifier.blur(blurRadius) else Modifier),
            state = lazyListState,
            contentPadding = paddingValues
        ) {
            //Poster
            item {
                DetailsPoster(
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/258fc3d6-8223-40ce-94ea-c87c2acc9f4b/1920x",
                    { startOffsetX },
                    { lazyListState.firstVisibleItemScrollOffset })
            }
            //Title, rating and fav button
            item {
                DetailsTitleRow(
                    rating = 9.0f,
                    movieName = "Матрица",
                    onFavoriteClick = {},
                    isFavorite = false
                )
            }
            //Expandable desc
            item {
                DetailsExpandableDescription(description = "Жизнь Томаса Андерсона разделена на две части: днём он — самый обычный офисный работник, получающий нагоняи от начальства, а ночью превращается в хакера по имени Нео, и нет места в сети, куда он бы не смог проникнуть. Но однажды всё меняется. Томас узнаёт ужасающую правду о реальности.")
            }
            //Genres
            item {
                DetailsGenreList(genresList)
            }
            //About section
            item {
                DetailsAboutSection(
                    year = 1999,
                    country = "США, Австралия",
                    tagLine = "Добро пожаловать в реальный мир",
                    director = "Лана Вачовски, Лилли Вачовски",
                    budget = 63000000,
                    fees = 463517383,
                    minimalAge = 16,
                    lengthMinutes = 136
                )
            }
            //Reviews header
            item {
                DetailsReviewHeader(
                    userHasNoReview = false,
                    onAddReview = null
                )
            }
            items(count = reviewList.size, key = { reviewList[it].id }) {
                ReviewElement(
                    review = reviewList[it],
                    shimmerOffsetProvider = { startOffsetX },
                    dateFormat = dateFormat,
                    isUserReview = it == 0,
                    onEditUserReview = { shouldShowReviewDialog = true },
                    onRemoveUserReview = { shouldShowReviewDialog = true }
                )
                Spacer(modifier = Modifier.height(PaddingLarge))
            }
        }
    }
    if (shouldShowReviewDialog) {
        ReviewDialog(
            dismissDialog = { shouldShowReviewDialog = false },
            saveReview = {},
            reviewTextProvider = { "" },
            setReviewText = {},
            ratingProvider = { 5 },
            setRating = {},
            isAnonymousProvider = { false },
            setAnonymous = {},
            anonymousCheckboxEnabled = true,
            saveButtonEnabled = { false }
        )
    }
}