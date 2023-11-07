package com.suslanium.filmus.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.filmus.presentation.ui.screen.favorite.FavoriteScreen
import com.suslanium.filmus.presentation.ui.screen.main.MainScreen
import com.suslanium.filmus.presentation.ui.screen.profile.ProfileScreen

object MainDestinations {
    const val MAIN = "main"
    const val FAVORITES = "favorites"
    const val PROFILE = "profile"
}

@Composable
fun MainNavigation(navHostController: NavHostController, rootNavHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = MainDestinations.MAIN) {
        composable(route = MainDestinations.MAIN) {
            MainScreen(rootNavHostController)
        }
        composable(route = MainDestinations.FAVORITES) {
            FavoriteScreen(rootNavHostController)
        }
        composable(route = MainDestinations.PROFILE) {
            ProfileScreen()
        }
    }
}