package com.suslanium.filmus.presentation.ui.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.suslanium.filmus.presentation.ui.theme.TextFieldOutlineColor
import com.suslanium.filmus.presentation.ui.theme.ButtonCornerRadius
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.OutlineBorderThickness
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.Red
import com.suslanium.filmus.presentation.ui.theme.SemiTransparentRed
import com.suslanium.filmus.presentation.ui.theme.S15_W500
import com.suslanium.filmus.presentation.ui.theme.S15_W400
import com.suslanium.filmus.presentation.ui.theme.TextFieldPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmusTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    errorMessage: String? = null,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = if (isError) SemiTransparentRed else Color.Transparent,
        unfocusedContainerColor = if (isError) SemiTransparentRed else Color.Transparent,
        disabledContainerColor = Color.Transparent,
        unfocusedBorderColor = TextFieldOutlineColor,
        focusedBorderColor = Gray400,
        disabledBorderColor = TextFieldOutlineColor,
        focusedTextColor = White,
        unfocusedTextColor = White,
        disabledTextColor = White,
        focusedTrailingIconColor = Gray400,
        unfocusedTrailingIconColor = Gray400,
        disabledTrailingIconColor = Gray400,
        errorContainerColor = SemiTransparentRed,
        errorTrailingIconColor = Gray400,
        errorBorderColor = Red,
        errorCursorColor = Red,
        cursorColor = TextFieldOutlineColor
    )
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(
            PaddingSmall
        )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = S15_W500,
            textAlign = TextAlign.Start,
            color = White
        )
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            textStyle = S15_W400.copy(color = White),
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            enabled = enabled,
            singleLine = true,
            cursorBrush = if (!isError) SolidColor(Gray400) else SolidColor(Red)
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                colors = colors,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                isError = isError,
                trailingIcon = trailingIcon,
                contentPadding = TextFieldPadding,
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = RoundedCornerShape(ButtonCornerRadius),
                        focusedBorderThickness = OutlinedTextFieldDefaults.FocusedBorderThickness,
                        unfocusedBorderThickness = OutlineBorderThickness
                    )
                },
            )
        }
        if (isError && errorMessage != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorMessage,
                style = S15_W400,
                textAlign = TextAlign.Start,
                color = Red
            )
        }
    }
}