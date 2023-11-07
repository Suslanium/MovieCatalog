package com.suslanium.filmus.presentation.ui.screen.details.components.reviewdialog

import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.Red
import com.suslanium.filmus.presentation.ui.theme.S14_W400
import com.suslanium.filmus.presentation.ui.theme.S15_W400
import com.suslanium.filmus.presentation.ui.theme.SemiTransparentRed
import com.suslanium.filmus.presentation.ui.theme.TextFieldOutlineColor

@Composable
fun ReviewTextField(
    reviewTextProvider: () -> String,
    setReviewText: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = reviewTextProvider(),
        onValueChange = setReviewText,
        modifier = Modifier
            .height(98.dp)
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = if (isError) SemiTransparentRed else Color.Transparent,
            unfocusedContainerColor = if (isError) SemiTransparentRed else Color.Transparent,
            disabledContainerColor = if (isError) SemiTransparentRed else Color.Transparent,
            unfocusedBorderColor = TextFieldOutlineColor,
            focusedBorderColor = Gray400,
            disabledBorderColor = TextFieldOutlineColor,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.White,
            cursorColor = TextFieldOutlineColor,
            focusedPlaceholderColor = Gray400,
            unfocusedPlaceholderColor = Gray400,
            disabledPlaceholderColor = Gray400,
            errorBorderColor = Red,
            errorCursorColor = Red,
            errorPlaceholderColor = Red
        ),
        shape = RoundedCornerShape(3.dp),
        placeholder = {
            Text(
                text = stringResource(id = R.string.write_review),
                style = S14_W400,
                color = Gray400
            )
        },
        isError = isError,
        enabled = enabled
    )
    if (isError && errorMessage != null) {
        Spacer(modifier = Modifier.height(PaddingSmall))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = errorMessage,
            style = S15_W400,
            textAlign = TextAlign.Start,
            color = Red
        )
    }
}