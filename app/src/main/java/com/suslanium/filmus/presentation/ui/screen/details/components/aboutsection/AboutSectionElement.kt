package com.suslanium.filmus.presentation.ui.screen.details.components.aboutsection

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.S14_W400
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun AboutSectionElement(modifier: Modifier, name: String, value: String) {
    Row(modifier = modifier) {
        Text(modifier = Modifier.width(108.dp), text = name, color = Gray400, style = S14_W400)
        Text(modifier = Modifier.weight(DefaultWeight), text = value, color = White, style = S14_W400)
    }
}