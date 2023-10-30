package com.suslanium.filmus.presentation.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.filmus.presentation.ui.screen.launch.LaunchScreen
import com.suslanium.filmus.presentation.ui.screen.login.LoginScreen
import com.suslanium.filmus.presentation.ui.screen.main.MainScreen
import com.suslanium.filmus.presentation.ui.screen.onboarding.OnboardingScreen
import com.suslanium.filmus.presentation.ui.screen.registration.RegistrationScreen

object FilmusDestinations {
    const val LAUNCH = "launch"
    const val ONBOARDING = "onboarding"
    const val REGISTRATION = "registration"
    const val LOGIN = "login"
    const val MAIN = "main"
}

@Composable
fun FilmusNavigation(navHostController: NavHostController, onAppExit: () -> Unit) {
    NavHost(navController = navHostController, startDestination = FilmusDestinations.LAUNCH) {
        composable(route = FilmusDestinations.LAUNCH) {
            LaunchScreen(navController = navHostController)
        }
        composable(route = FilmusDestinations.ONBOARDING) {
            OnboardingScreen(navController = navHostController)
            BackHandler {
                onAppExit()
            }
        }
        composable(route = FilmusDestinations.REGISTRATION) {
            RegistrationScreen(navController = navHostController)
        }
        composable(route = FilmusDestinations.LOGIN) {
            LoginScreen(navController = navHostController)
        }
        composable(route = FilmusDestinations.MAIN) {
            MainScreen()
        }
    }
}