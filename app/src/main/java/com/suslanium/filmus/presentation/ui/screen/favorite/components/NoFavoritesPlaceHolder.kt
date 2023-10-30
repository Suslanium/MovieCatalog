package com.suslanium.filmus.presentation.ui.screen.favorite.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.S15_W400
import com.suslanium.filmus.presentation.ui.theme.S20_W700
import com.suslanium.filmus.presentation.ui.theme.White

fun LazyListScope.noFavoritesPlaceHolder() {
    item {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_favorites_title),
                style = S20_W700,
                color = White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_favorites_message),
                style = S15_W400,
                color = White,
                textAlign = TextAlign.Center
            )
        }
    }
}