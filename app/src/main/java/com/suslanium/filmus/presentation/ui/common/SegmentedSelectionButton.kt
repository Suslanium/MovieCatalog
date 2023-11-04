package com.suslanium.filmus.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonBorder
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.presentation.ui.theme.AuthFieldHeight
import com.suslanium.filmus.presentation.ui.theme.ButtonCornerRadius
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.Gray750
import com.suslanium.filmus.presentation.ui.theme.LightTertiary
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S14_W400
import com.suslanium.filmus.presentation.ui.theme.S15_W500
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.ui.theme.ZeroDp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedSelectionButton(
    title: String,
    selectedIndex: Int,
    options: List<String>,
    onItemSelected: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(
            PaddingSmall
        )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = S15_W500,
            textAlign = TextAlign.Start,
            color = Color.White
        )
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .background(
                    color = LightTertiary, shape = RoundedCornerShape(
                        ButtonCornerRadius
                    )
                ).padding(2.dp)
                .fillMaxWidth()
                .height(AuthFieldHeight)
        ) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    selected = index == selectedIndex,
                    onClick = { onItemSelected(index) },
                    shape = RoundedCornerShape(
                        ButtonCornerRadius
                    ),
                    icon = {},
                    border = SegmentedButtonBorder(ZeroDp),
                    colors = SegmentedButtonColors(
                        activeContainerColor = White,
                        activeContentColor = Gray750,
                        inactiveContainerColor = Color.Transparent,
                        inactiveContentColor = Gray400,
                        inactiveBorderColor = Color.Transparent,
                        activeBorderColor = Color.Transparent,
                        disabledActiveContainerColor = White,
                        disabledActiveContentColor = Gray750,
                        disabledInactiveContainerColor = Color.Transparent,
                        disabledInactiveContentColor = Gray400,
                        disabledInactiveBorderColor = Color.Transparent,
                        disabledActiveBorderColor = Color.Transparent
                    )
                ) {
                    Text(text = label, style = S14_W400)
                }
            }
        }
    }
}