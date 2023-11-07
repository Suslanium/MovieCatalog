package com.suslanium.filmus.presentation.ui.navigation

import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.suslanium.filmus.presentation.ui.screen.details.DetailsScreen
import com.suslanium.filmus.presentation.ui.screen.launch.LaunchScreen
import com.suslanium.filmus.presentation.ui.screen.login.LoginScreen
import com.suslanium.filmus.presentation.ui.screen.mainroot.MainScreenRoot
import com.suslanium.filmus.presentation.ui.screen.onboarding.OnboardingScreen
import com.suslanium.filmus.presentation.ui.screen.registration.RegistrationScreen
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.NavBarBackground
import java.util.UUID

object FilmusDestinations {
    const val LAUNCH = "launch"
    const val ONBOARDING = "onboarding"
    const val REGISTRATION = "registration"
    const val LOGIN = "login"
    const val MAIN = "main"
    const val DETAILS = "details/{movieId}"
    const val DETAILS_NO_ID = "details"
}

@Composable
fun FilmusNavigation(
    navHostController: NavHostController,
    onAppExit: () -> Unit,
    enableEdgeToEdge: (SystemBarStyle, SystemBarStyle) -> Unit
) {
    NavHost(navController = navHostController, startDestination = FilmusDestinations.LAUNCH) {
        composable(route = FilmusDestinations.LAUNCH) {
            enableEdgeToEdge(
                SystemBarStyle.dark(Background.toArgb()), SystemBarStyle.dark(
                    Background.toArgb()
                )
            )
            LaunchScreen(navController = navHostController)
        }
        composable(route = FilmusDestinations.ONBOARDING) {
            enableEdgeToEdge(
                SystemBarStyle.dark(Background.toArgb()), SystemBarStyle.dark(
                    Background.toArgb()
                )
            )
            OnboardingScreen(navController = navHostController)
            BackHandler {
                onAppExit()
            }
        }
        composable(route = FilmusDestinations.REGISTRATION) {
            enableEdgeToEdge(
                SystemBarStyle.dark(Background.toArgb()), SystemBarStyle.dark(
                    Background.toArgb()
                )
            )
            RegistrationScreen(navController = navHostController)
        }
        composable(route = FilmusDestinations.LOGIN) {
            enableEdgeToEdge(
                SystemBarStyle.dark(Background.toArgb()), SystemBarStyle.dark(
                    Background.toArgb()
                )
            )
            LoginScreen(navController = navHostController)
        }
        composable(route = FilmusDestinations.MAIN) {
            enableEdgeToEdge(
                SystemBarStyle.dark(Background.toArgb()), SystemBarStyle.dark(
                    NavBarBackground.toArgb()
                )
            )
            MainScreenRoot(onAppExit = onAppExit, rootNavController = navHostController)
        }
        composable(route = FilmusDestinations.DETAILS, arguments = listOf(navArgument("movieId") { type = NavType.StringType })) {
            enableEdgeToEdge(
                SystemBarStyle.dark(Background.toArgb()), SystemBarStyle.dark(
                    Background.toArgb()
                )
            )
            DetailsScreen(UUID.fromString(it.arguments?.getString("movieId")), navHostController)
        }
    }
}