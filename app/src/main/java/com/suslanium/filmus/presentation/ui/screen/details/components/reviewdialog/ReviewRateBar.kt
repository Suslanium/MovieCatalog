package com.suslanium.filmus.presentation.ui.screen.details.components.reviewdialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.PaddingUltraSmall

@Composable
fun ReviewRateBar(setRating: (Int) -> Unit, ratingProvider: () -> Int) {
    Row(
        modifier = Modifier
            .padding(PaddingUltraSmall)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(10) {
            Icon(
                modifier = Modifier.clickable { setRating(it + 1) },
                imageVector = ImageVector.vectorResource(id = if (it + 1 <= ratingProvider()) R.drawable.rating_star_filled else R.drawable.rating_star_outline),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}