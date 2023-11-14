package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.S16_W700
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun DetailsReviewHeader(
    userHasNoReview: Boolean,
    onAddReview: (() -> Unit)?
) {
    Spacer(modifier = Modifier.height(PaddingLarge).fillMaxWidth().background(Background))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(horizontal = PaddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(
                id = R.string.reviews
            ), style = S16_W700, color = White
        )
        if (userHasNoReview) {
            IconButton(
                onClick = {
                    if (onAddReview != null) {
                        onAddReview()
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(32.dp),
                colors = IconButtonColors(
                    containerColor = Accent,
                    contentColor = White,
                    disabledContentColor = White,
                    disabledContainerColor = Accent
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.plus_icon),
                    contentDescription = null
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp).fillMaxWidth().background(Background))
}