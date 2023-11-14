package com.suslanium.filmus.presentation.ui.common

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.suslanium.filmus.presentation.common.Constants

@Composable
fun shimmerOffsetAnimation(transition: InfiniteTransition) =
    transition.animateFloat(
        initialValue = -2.8f,
        targetValue = 2.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = Constants.EMPTY_STRING
    )