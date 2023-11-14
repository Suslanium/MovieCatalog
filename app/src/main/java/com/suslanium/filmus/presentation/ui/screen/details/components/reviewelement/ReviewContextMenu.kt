package com.suslanium.filmus.presentation.ui.screen.details.components.reviewelement

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.shimmerEffect
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.Red
import com.suslanium.filmus.presentation.ui.theme.S14_W500
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun ReviewContextMenu(
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit,
    onEditUserReview: (() -> Unit)?,
    onRemoveUserReview: (() -> Unit)?,
    shimmerProvider: (() -> Float),
    showShimmer: () -> Boolean
) {
    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(10.dp))
    ) {
        DropdownMenu(modifier = Modifier.background(Gray750),
            expanded = expanded,
            onDismissRequest = { if (!showShimmer()) setExpanded(false) }) {
            DropdownMenuItem(text = {
                Text(
                    text = stringResource(id = R.string.edit), style = S14_W500, color = White
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
            DropdownMenuItem(modifier = Modifier.then(if (showShimmer()) Modifier.shimmerEffect(shimmerProvider, backgroundColor = Gray750, shimmerColor = Red) else Modifier),
                text = {
                    Text(
                        text = stringResource(id = R.string.delete), style = S14_W500, color = Red
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
}