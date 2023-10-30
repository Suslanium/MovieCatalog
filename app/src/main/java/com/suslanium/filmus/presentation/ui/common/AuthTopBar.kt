package com.suslanium.filmus.presentation.ui.common

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.S17_W600
import com.suslanium.filmus.presentation.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTopBar(
    onNavigateBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Background,
        navigationIconContentColor = White,
        titleContentColor = Accent
    ), title = {
        Text(
            text = stringResource(id = R.string.app_name),
            style = S17_W600,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }, navigationIcon = {
        IconButton(onClick = onNavigateBackClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.back_icon),
                contentDescription = null,
                tint = White
            )
        }
    })
}