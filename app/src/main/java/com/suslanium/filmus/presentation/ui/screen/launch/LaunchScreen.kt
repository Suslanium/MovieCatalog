package com.suslanium.filmus.presentation.ui.screen.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.state.LaunchEvent
import com.suslanium.filmus.presentation.ui.common.ObserveAsEvents
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.viewmodel.LaunchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LaunchScreen(
    navController: NavController
) {
    val launchViewModel: LaunchViewModel = koinViewModel()

    ObserveAsEvents(flow = launchViewModel.launchEvents) { event ->
        when (event) {
            LaunchEvent.Unauthorized -> navController.navigate(FilmusDestinations.ONBOARDING)
            LaunchEvent.Authorized -> navController.navigate(FilmusDestinations.MAIN)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.launch_screen_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.logo_rounded),
            contentDescription = null
        )
    }
}