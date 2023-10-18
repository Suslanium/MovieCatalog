package com.suslanium.filmus.presentation.ui.screen.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.AuthTextField
import com.suslanium.filmus.presentation.ui.common.AuthTopBar
import com.suslanium.filmus.presentation.ui.common.Constants
import com.suslanium.filmus.presentation.ui.screen.registration.components.SegmentedSelectionButton
import com.suslanium.filmus.presentation.ui.theme.Accent
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.BottomHint
import com.suslanium.filmus.presentation.ui.theme.ButtonVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.DefaultWeight
import com.suslanium.filmus.presentation.ui.theme.Gray200
import com.suslanium.filmus.presentation.ui.theme.LoginVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.Title
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.ui.theme.WidthFraction

@Composable
fun RegistrationScreen() {
    val bottomHint = buildAnnotatedString {
        withStyle(
            style = SpanStyle(color = Gray200)
        ) {
            append(stringResource(id = R.string.already_have_an_account))
            append(" ")
        }

        pushStringAnnotation(Constants.AUTH_TAG, Constants.AUTH_TAG)
        withStyle(style = SpanStyle(color = Accent)) {
            append(stringResource(id = R.string.log_in_bottom_hint))
        }
        pop()
    }
    Scaffold(containerColor = Background, topBar = {
        AuthTopBar(onNavigateBackClick = { /*TODO*/ })
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(bottom = PaddingMedium),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(WidthFraction),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.registration),
                    color = White,
                    style = Title,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
                //Content goes here
                Spacer(modifier = Modifier.weight(DefaultWeight))
                ClickableText(text = bottomHint, style = BottomHint, onClick = { offset ->
                    bottomHint.getStringAnnotations(
                        tag = Constants.AUTH_TAG, start = offset, end = offset
                    ).firstOrNull()?.let {}
                })
            }
        }
    }
}

@Composable
private fun RegistrationCredentialsContent() {
    AuthTextField(title = stringResource(id = R.string.password),
        value = "",
        onValueChange = {},
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.visibility_icon),
                    contentDescription = null
                )
            }
        })
    Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
    AuthTextField(title = stringResource(id = R.string.repeat_password),
        value = "",
        onValueChange = {},
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.visibility_icon),
                    contentDescription = null
                )
            }
        })
    Spacer(modifier = Modifier.height(LoginVerticalSpacing))
    AccentButton(
        onClick = { /*TODO*/ },
        text = stringResource(id = R.string.register_button),
        modifier = Modifier.fillMaxWidth(),
        enabled = false
    )
}

@Composable
private fun RegistrationPersonalInfoContent() {
    AuthTextField(
        title = stringResource(id = R.string.name),
        value = "",
        onValueChange = {})
    Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
    SegmentedSelectionButton(title = stringResource(id = R.string.gender),
        options = listOf(
            stringResource(id = R.string.male), stringResource(id = R.string.female)
        ),
        selectedIndex = 0,
        onItemSelected = {})
    Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
    AuthTextField(
        title = stringResource(id = R.string.login),
        value = "",
        onValueChange = {},
    )
    Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
    AuthTextField(
        title = stringResource(id = R.string.email),
        value = "",
        onValueChange = {})
    Spacer(modifier = Modifier.height(ButtonVerticalSpacing))
    AuthTextField(title = stringResource(id = R.string.birthdate),
        value = "",
        onValueChange = {},
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.calendar_icon),
                    contentDescription = null
                )
            }
        })
    Spacer(modifier = Modifier.height(LoginVerticalSpacing))
    AccentButton(
        onClick = { /*TODO*/ },
        text = stringResource(id = R.string.continue_button),
        modifier = Modifier.fillMaxWidth(),
        enabled = false
    )
}

@Preview
@Composable
fun RegistrationPreview() {
    RegistrationScreen()
}