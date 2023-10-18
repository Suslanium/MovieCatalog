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
import com.suslanium.filmus.presentation.ui.theme.AuthFieldOutlineColor
import com.suslanium.filmus.presentation.ui.theme.ButtonCornerRadius
import com.suslanium.filmus.presentation.ui.theme.Gray400
import com.suslanium.filmus.presentation.ui.theme.OutlineBorderThickness
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.Red
import com.suslanium.filmus.presentation.ui.theme.SemiTransparentRed
import com.suslanium.filmus.presentation.ui.theme.SubTitleMedium
import com.suslanium.filmus.presentation.ui.theme.TextFieldInput
import com.suslanium.filmus.presentation.ui.theme.TextFieldPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
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
        unfocusedBorderColor = AuthFieldOutlineColor,
        focusedBorderColor = Gray400,
        focusedTextColor = White,
        unfocusedTextColor = White,
        focusedTrailingIconColor = Gray400,
        unfocusedTrailingIconColor = Gray400,
        errorTrailingIconColor = Gray400,
        errorBorderColor = Red,
        errorCursorColor = Red,
        cursorColor = AuthFieldOutlineColor
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
            style = SubTitleMedium,
            textAlign = TextAlign.Start,
            color = White
        )
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextFieldInput.copy(color = White),
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            enabled = true,
            singleLine = true,
            cursorBrush = SolidColor(Gray400)
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                colors = colors,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                isError = isError,
                trailingIcon = trailingIcon,
                contentPadding = TextFieldPadding,
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = true,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = RoundedCornerShape(ButtonCornerRadius),
                        focusedBorderThickness = OutlinedTextFieldDefaults.FocusedBorderThickness,
                        unfocusedBorderThickness = OutlineBorderThickness,
                    )
                },
            )
        }
        if (isError && errorMessage != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorMessage,
                style = TextFieldInput,
                textAlign = TextAlign.Start,
                color = Red
            )
        }
    }
}