package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.suslanium.filmus.R
import com.suslanium.filmus.domain.entity.movie.Review
import com.suslanium.filmus.presentation.ui.common.UserRating
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.GrayAvatarBackground
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.PaddingUltraSmall
import com.suslanium.filmus.presentation.ui.theme.Red
import com.suslanium.filmus.presentation.ui.theme.S12_W500
import com.suslanium.filmus.presentation.ui.theme.S13_W400
import com.suslanium.filmus.presentation.ui.theme.S14_W400
import com.suslanium.filmus.presentation.ui.theme.S14_W500
import com.suslanium.filmus.presentation.ui.theme.White
import java.time.format.DateTimeFormatter

@Composable
fun ReviewElement(
    review: Review,
    shimmerOffset: Float,
    dateFormat: DateTimeFormatter,
    isUserReview: Boolean = false,
    onEditUserReview: (() -> Unit)? = null,
    onRemoveUserReview: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GlideImage(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(GrayAvatarBackground),
                imageModel = { if (!review.isAnonymous) review.author?.avatar else null },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmerEffect(
                                startOffsetX = shimmerOffset,
                                backgroundColor = Gray750,
                                shimmerColor = Accent
                            )
                    )
                }, failure = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(GrayAvatarBackground), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.no_avatar_icon),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.width(PaddingLarge / 2))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (review.isAnonymous) stringResource(id = R.string.anonymous_user) else review.author?.nickName.orEmpty(),
                    style = S14_W500,
                    color = White,
                    maxLines = 1
                )
                if (isUserReview) {
                    Spacer(modifier = Modifier.height(PaddingUltraSmall))
                    Text(
                        text = stringResource(id = R.string.my_review),
                        style = S13_W400,
                        color = Gray400,
                        maxLines = 1
                    )
                }
            }
            UserRating(userRating = review.rating)
            if (isUserReview) {
                var expanded by remember { mutableStateOf(false) }
                Spacer(modifier = Modifier.width(PaddingLarge / 2))
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(26.dp),
                    colors = IconButtonColors(
                        containerColor = Gray750,
                        contentColor = White,
                        disabledContentColor = White,
                        disabledContainerColor = Gray750
                    )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.review_context_icon),
                        contentDescription = null
                    )
                    ReviewContextMenu(
                        expanded,
                        { expanded = it },
                        onEditUserReview,
                        onRemoveUserReview
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(PaddingSmall))
        Text(
            text = review.reviewText.orEmpty(),
            style = S14_W400,
            color = White
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = dateFormat.format(review.creationDateTime), style = S12_W500, color = Gray400)
    }
}

@Composable
fun ReviewContextMenu(
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit,
    onEditUserReview: (() -> Unit)?,
    onRemoveUserReview: (() -> Unit)?
) {
    DropdownMenu(
        modifier = Modifier.background(Gray750, RoundedCornerShape(10.dp)),
        expanded = expanded,
        onDismissRequest = { setExpanded(false) }) {
        DropdownMenuItem(text = {
            Text(
                text = stringResource(id = R.string.edit),
                style = S14_W500,
                color = White
            )
        }, onClick = {
            if (onEditUserReview != null) {
                onEditUserReview()
            }
        }, trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.edit_review_icon),
                contentDescription = null
            )
        })
        HorizontalDivider(color = Color(0xFF55595D))
        DropdownMenuItem(text = {
            Text(
                text = stringResource(id = R.string.delete),
                style = S14_W500,
                color = Red
            )
        }, onClick = {
            if (onRemoveUserReview != null) {
                onRemoveUserReview()
            }
        }, trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.delete_review_icon),
                contentDescription = null,
                tint = Red
            )
        })
    }
}
