package com.suslanium.filmus.presentation.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.screen.main.components.PosterCarousel
import java.util.UUID

@Composable
fun MainScreen() {
    val moviesList = listOf(
        MovieSummary(
            UUID.randomUUID(),
            null,
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1704946/90e751c7-88ba-4508-93a9-952b5b25500c/1920x",
            2000,
            null,
            emptyList(),
            null,
            null
        ),
        MovieSummary(
            UUID.randomUUID(),
            null,
            "https://avatars.mds.yandex.net/get-kinopoisk-image/4303601/9eb762d6-4cdd-464f-9937-aebf30067acc/1920x",
            2000,
            null,
            emptyList(),
            null,
            null
        ),
        MovieSummary(
            UUID.randomUUID(),
            null,
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/e1f881aa-20c4-4fe5-b6dd-8b8b69c4e639/1920x",
            2000,
            null,
            emptyList(),
            null,
            null
        ),
        MovieSummary(
            UUID.randomUUID(),
            null,
            "https://avatars.mds.yandex.net/get-kinopoisk-image/1599028/29c4da3f-75ee-403a-80d1-9f55b77cac0d/1920x",
            2000,
            null,
            emptyList(),
            null,
            null
        )
    )
    Column(modifier = Modifier.fillMaxSize()) {
        PosterCarousel(movies = moviesList)
    }
}