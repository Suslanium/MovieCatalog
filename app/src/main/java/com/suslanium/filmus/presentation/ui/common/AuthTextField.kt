package com.suslanium.filmus.presentation.ui.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
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
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        containerColor = if (isError) SemiTransparentRed else Color.Transparent,
        unfocusedBorderColor = AuthFieldOutlineColor,
        focusedBorderColor = Gray400,
        textColor = White,
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
            textStyle = TextFieldInput,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            enabled = true,
            singleLine = true,
        ) { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                contentPadding = TextFieldPadding,
                container = {
                    TextFieldDefaults.OutlinedBorderContainerBox(
                        enabled = true,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = RoundedCornerShape(ButtonCornerRadius),
                        unfocusedBorderThickness = OutlineBorderThickness
                    )
                },
                colors = colors,
                isError = isError,
                trailingIcon = trailingIcon
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