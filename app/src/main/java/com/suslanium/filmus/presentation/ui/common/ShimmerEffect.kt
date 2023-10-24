package com.suslanium.filmus.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmerEffect(
    startOffsetX: Float,
    backgroundColor: Color = Color.Gray,
    shimmerColor: Color = Color.DarkGray
): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                backgroundColor,
                shimmerColor,
                backgroundColor
            ),
            start = Offset(startOffsetX * size.width.toFloat(), 0f),
            end = Offset(startOffsetX * size.width.toFloat() + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}