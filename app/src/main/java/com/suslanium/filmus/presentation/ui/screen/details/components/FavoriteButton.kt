package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.Gray750

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    changeFavoritesState: () -> Unit,
    isFavorite: Boolean,
) {
    IconButton(
        onClick = changeFavoritesState, modifier = modifier
            .size(40.dp)
            .clip(
                CircleShape
            ), colors = IconButtonDefaults.iconButtonColors(
            containerColor = Gray750, disabledContainerColor = Gray750
        )
    ) {
        Icon(
            imageVector = if (isFavorite) ImageVector.vectorResource(
                R.drawable.fav_button_icon
            ) else ImageVector.vectorResource(
                R.drawable.fav_button_icon_filled
            ), contentDescription = null, tint = Color.Unspecified
        )
    }
}