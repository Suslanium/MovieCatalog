package com.suslanium.filmus.presentation.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.Genre
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.screen.main.components.PosterCarousel
import com.suslanium.filmus.presentation.ui.screen.main.components.moviecard.MovieCard
import com.suslanium.filmus.presentation.ui.theme.MovieTitle
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.White
import java.util.UUID

@Composable
fun MainScreen() {
    val moviesList = listOf(
        MovieSummary(
            UUID.randomUUID(),
            "Фокус",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/90e751c7-88ba-4508-93a9-952b5b25500c/1920x",
            2014,
            "CША",
            listOf(
                Genre(UUID.randomUUID(), "драма"),
                Genre(UUID.randomUUID(), "мелодрама"),
                Genre(UUID.randomUUID(), "комедия"),
                Genre(UUID.randomUUID(), "криминал")
            ),
            7.0f,
            7
        ), MovieSummary(
            UUID.randomUUID(),
            "Дюна",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/4303601/9eb762d6-4cdd-464f-9937-aebf30067acc/1920x",
            2021,
            "США",
            listOf(
                Genre(UUID.randomUUID(), "фантастика"),
                Genre(UUID.randomUUID(), "боевик"),
                Genre(UUID.randomUUID(), "драма"),
                Genre(UUID.randomUUID(), "приключения")
            ),
            7.7f,
            8
        ), MovieSummary(
            UUID.randomUUID(),
            "Марсианин",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/e1f881aa-20c4-4fe5-b6dd-8b8b69c4e639/1920x",
            2015,
            "США",
            listOf(
                Genre(UUID.randomUUID(), "фантастика"), Genre(UUID.randomUUID(), "приключения")
            ),
            7.8f,
            10
        ), MovieSummary(
            UUID.randomUUID(),
            "Богемская рапсодия",
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1599028/29c4da3f-75ee-403a-80d1-9f55b77cac0d/1920x",
            2018,
            "Великобритания",
            listOf(
                Genre(UUID.randomUUID(), "биография"),
                Genre(UUID.randomUUID(), "музыка"),
                Genre(UUID.randomUUID(), "драма")
            ),
            8.0f,
            9
        )
    )
    LazyColumn(modifier = Modifier.fillMaxSize(), state = rememberLazyListState()) {
        item {
            PosterCarousel(movies = moviesList)
        }
        item {
            Text(
                modifier = Modifier.padding(
                    start = PaddingMedium, end = PaddingMedium, top = PaddingMedium, bottom = 15.dp
                ),
                text = stringResource(id = R.string.catalog),
                style = MovieTitle,
                color = White,
                textAlign = TextAlign.Start
            )
        }
        items(count = moviesList.size, key = { moviesList[it].id }) {
            MovieCard(
                movieSummary = moviesList[it], modifier = Modifier.padding(
                    start = PaddingMedium, end = PaddingMedium, bottom = PaddingMedium
                )
            )
        }
    }
}