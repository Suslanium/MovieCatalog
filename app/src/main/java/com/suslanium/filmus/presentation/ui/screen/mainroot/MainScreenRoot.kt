package com.suslanium.filmus.presentation.ui.screen.mainroot

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.suslanium.filmus.presentation.ui.navigation.MainNavigation
import com.suslanium.filmus.presentation.ui.screen.mainroot.components.BottomNavigationBar

@Composable
fun MainScreenRoot(
    onAppExit: () -> Unit,
    rootNavController: NavHostController
) {
    val navController = rememberNavController()
    BackHandler {
        if (navController.previousBackStackEntry == null) onAppExit()
        else navController.popBackStack()
    }
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        BottomNavigationBar(
            navController = navController,
            onItemClick = { navController.navigate(it.route) }
        )
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            MainNavigation(
                navHostController = navController,
                rootNavHostController = rootNavController
            )
        }
    }
}