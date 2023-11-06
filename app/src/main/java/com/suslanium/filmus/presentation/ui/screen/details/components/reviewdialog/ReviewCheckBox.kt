package com.suslanium.filmus.presentation.ui.screen.details.components.reviewdialog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Gray100
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S15_W500
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun ReviewCheckBox(
    isAnonymousProvider: () -> Boolean,
    setAnonymous: (Boolean) -> Unit,
    anonymousCheckboxEnabled: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            modifier = Modifier
                .size(16.dp),
            checked = isAnonymousProvider(),
            onCheckedChange = setAnonymous,
            enabled = anonymousCheckboxEnabled,
            colors = CheckboxDefaults.colors(
                checkedColor = Accent,
                uncheckedColor = Gray100,
                checkmarkColor = White,
                disabledCheckedColor = Gray400,

                )
        )
        Spacer(modifier = Modifier.width(PaddingSmall))
        Text(
            text = stringResource(id = R.string.anonymous_review),
            style = S15_W500,
            color = White
        )
    }
}