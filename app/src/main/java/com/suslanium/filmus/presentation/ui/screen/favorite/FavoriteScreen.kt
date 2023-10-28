package com.suslanium.filmus.presentation.ui.screen.favorite

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.Genre
import com.suslanium.filmus.domain.entity.movie.MovieSummary
import com.suslanium.filmus.presentation.ui.screen.favorite.components.favoritesList
import com.suslanium.filmus.presentation.ui.screen.favorite.components.noFavoritesPlaceHolder
import com.suslanium.filmus.presentation.ui.theme.FilmusTheme
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingMediumLarge
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.White
import java.util.UUID

@Composable
fun FavoriteScreen() {
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
            null
        )
    )
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2.8f,
        targetValue = 2.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingMedium),
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(PaddingMediumLarge)
    ) {
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.favorites),
                style = S24_W700,
                color = White,
                textAlign = TextAlign.Center
            )
        }
        if (moviesList.isNotEmpty()) {
            favoritesList(moviesList, startOffsetX)
        } else {
            noFavoritesPlaceHolder()
        }
    }
}

@Preview
@Composable
fun FavoritePreview() {
    FilmusTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            FavoriteScreen()
        }
    }
}