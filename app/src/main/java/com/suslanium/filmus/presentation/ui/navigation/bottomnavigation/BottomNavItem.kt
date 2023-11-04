package com.suslanium.filmus.presentation.ui.navigation.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    @StringRes
    val name: Int,
    val route: String,
    @DrawableRes
    val iconId: Int
)