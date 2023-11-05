package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.colorByRating
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingExtraSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S15_W500
import com.suslanium.filmus.presentation.ui.theme.S24_W700
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun DetailsTitleRow(
    rating: Float,
    movieName: String,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean
) {
    Spacer(modifier = Modifier.height(PaddingMedium))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(26.dp)
                .width(51.dp)
                .clip(
                    RoundedCornerShape(
                        MovieCardCornerRadiusMedium
                    )
                )
                .background(
                    colorByRating(rating)
                )
                .padding(vertical = PaddingExtraSmall, horizontal = PaddingSmall),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = String.format("%.1f", rating),
                textAlign = TextAlign.Center,
                style = S15_W500,
                color = White /*TODO COLOR BY RATING*/
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = movieName,
            textAlign = TextAlign.Center,
            style = S24_W700,
            color = White
        )
        IconButton(
            onClick = onFavoriteClick, modifier = Modifier
                .size(40.dp)
                .clip(
                    CircleShape
                ), colors = IconButtonDefaults.iconButtonColors(
                containerColor = Gray750, disabledContainerColor = Gray750
            )
        ) {
            /*TODO Favorite icon*/
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.fav_button_icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}