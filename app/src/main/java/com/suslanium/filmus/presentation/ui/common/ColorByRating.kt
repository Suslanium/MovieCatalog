package com.suslanium.filmus.presentation.ui.common

import androidx.compose.ui.graphics.Color
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.RatingLessOrEqual2
import com.suslanium.filmus.presentation.ui.theme.RatingOver2
import com.suslanium.filmus.presentation.ui.theme.RatingOver3
import com.suslanium.filmus.presentation.ui.theme.RatingOver4
import com.suslanium.filmus.presentation.ui.theme.RatingOver6
import com.suslanium.filmus.presentation.ui.theme.RatingOver8
import com.suslanium.filmus.presentation.ui.theme.White

fun colorByRating(rating: Float): Color {
    return when {
        rating > 8 -> RatingOver8
        rating > 6 -> RatingOver6
        rating > 4 -> RatingOver4
        rating > 3 -> RatingOver3
        rating > 2 -> RatingOver2
        else -> RatingLessOrEqual2
    }
}

fun textColorByRating(rating: Float): Color {
    return when {
        rating > 4 && rating <= 8 -> Background
        else -> White
    }
}