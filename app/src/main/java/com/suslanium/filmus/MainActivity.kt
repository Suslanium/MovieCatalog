package com.suslanium.filmus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.suslanium.filmus.presentation.ui.navigation.FilmusNavigation
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.FilmusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmusTheme {
                enableEdgeToEdge(SystemBarStyle.dark(Background.toArgb()), SystemBarStyle.dark(Background.toArgb()))
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    FilmusNavigation(navHostController = navController, onAppExit = {
                        finish()
                    })
                }
            }
        }
    }
}