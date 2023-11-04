package com.suslanium.filmus.presentation.ui.navigation.bottomnavigation

import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.navigation.MainDestinations

object BottomNavItems {
    val bottomNavItems = listOf(
        BottomNavItem(
            name = R.string.main,
            route = MainDestinations.MAIN,
            iconId = R.drawable.main_icon
        ),
        BottomNavItem(
            name = R.string.favorites,
            route = MainDestinations.FAVORITES,
            iconId = R.drawable.favorite_icon
        ),
        BottomNavItem(
            name = R.string.profile,
            route = MainDestinations.PROFILE,
            iconId = R.drawable.profile_icon
        )
    )
}