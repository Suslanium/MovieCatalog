package com.suslanium.filmus.presentation.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.ButtonCornerRadius
import com.suslanium.filmus.presentation.ui.theme.ButtonPadding
import com.suslanium.filmus.presentation.ui.theme.ButtonText
import com.suslanium.filmus.presentation.ui.theme.DisabledAccent
import com.suslanium.filmus.presentation.ui.theme.DisabledMain
import com.suslanium.filmus.presentation.ui.theme.DisabledWhite
import com.suslanium.filmus.presentation.ui.theme.Main
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    cornerRadius: Dp = ButtonCornerRadius,
    buttonColors: ButtonColors,
    paddingValues: PaddingValues = ButtonPadding,
    text: String,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = buttonColors,
        enabled = enabled,
        contentPadding = paddingValues
    ) {
        Text(text = text, style = ButtonText, textAlign = TextAlign.Center)
    }
}

@Composable
fun AccentButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    cornerRadius: Dp = ButtonCornerRadius,
    paddingValues: PaddingValues = ButtonPadding,
    text: String,
    enabled: Boolean = true
) {
    AppButton(
        modifier = modifier, onClick = onClick, buttonColors = ButtonDefaults.buttonColors(
            contentColor = White,
            containerColor = Accent,
            disabledContainerColor = DisabledAccent,
            disabledContentColor = DisabledWhite
        ), paddingValues = paddingValues, text = text, cornerRadius = cornerRadius, enabled = enabled
    )
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    cornerRadius: Dp = ButtonCornerRadius,
    paddingValues: PaddingValues = ButtonPadding,
    text: String,
    enabled: Boolean = true
) {
    AppButton(
        modifier = modifier, onClick = onClick, buttonColors = ButtonDefaults.buttonColors(
            contentColor = Accent,
            containerColor = Main,
            disabledContentColor = DisabledAccent,
            disabledContainerColor = DisabledMain
        ), paddingValues = paddingValues, text = text, enabled = enabled, cornerRadius = cornerRadius
    )
}