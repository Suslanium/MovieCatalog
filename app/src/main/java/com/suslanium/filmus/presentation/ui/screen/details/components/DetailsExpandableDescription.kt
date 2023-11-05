package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.NoRippleInteractionSource
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S14_W400
import com.suslanium.filmus.presentation.ui.theme.S14_W500
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun DetailsExpandableDescription(description: String) {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    val isExpandable by remember {
        derivedStateOf {
            textLayoutResult?.didOverflowHeight ?: false
        }
    }
    var isExpanded by remember { mutableStateOf(false) }
    val isButtonShown by remember { derivedStateOf { isExpandable || isExpanded } }

    Spacer(modifier = Modifier.height(PaddingLarge))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = PaddingMedium)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            if (isExpanded) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = description,
                    style = S14_W400,
                    color = White
                )
            } else {
                Text(modifier = Modifier
                    .fillMaxWidth(),
                    text = description,
                    style = S14_W400,
                    color = White,
                    maxLines = 3,
                    overflow = TextOverflow.Clip,
                    onTextLayout = { textLayoutResult = it })
            }
        }

        if (isExpandable && !isExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0x001D1D1D), Color(0xC71D1D1D), Color(0xFF1D1D1D)
                            )
                        )
                    )
            )
        }
    }
    if (isButtonShown) {
        Spacer(modifier = Modifier.height(6.5.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = PaddingMedium)
                .clickable(
                    onClick = { isExpanded = !isExpanded },
                    interactionSource = NoRippleInteractionSource(),
                    indication = null
                ), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.more),
                style = S14_W500,
                color = Accent
            )
            Spacer(modifier = Modifier.width(PaddingSmall))
            Icon(
                imageVector = ImageVector.vectorResource(if (isExpanded) R.drawable.collapse_icon else R.drawable.expand_icon),
                contentDescription = null,
                tint = Accent
            )
        }
    }
}