package com.suslanium.filmus.presentation.ui.screen.details.components.reviewdialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.S14_W400
import com.suslanium.filmus.presentation.ui.theme.TextFieldOutlineColor

@Composable
fun ReviewTextField(
    reviewTextProvider: () -> String,
    setReviewText: (String) -> Unit
) {
    OutlinedTextField(
        value = reviewTextProvider(),
        onValueChange = setReviewText,
        modifier = Modifier
            .height(98.dp)
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            unfocusedBorderColor = TextFieldOutlineColor,
            focusedBorderColor = Gray400,
            disabledBorderColor = TextFieldOutlineColor,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.White,
            cursorColor = TextFieldOutlineColor,
            focusedPlaceholderColor = Gray400,
            unfocusedPlaceholderColor = Gray400,
            disabledPlaceholderColor = Gray400
        ),
        shape = RoundedCornerShape(3.dp),
        placeholder = {
            Text(
                text = stringResource(id = R.string.write_review),
                style = S14_W400,
                color = Gray400
            )
        }
    )
}