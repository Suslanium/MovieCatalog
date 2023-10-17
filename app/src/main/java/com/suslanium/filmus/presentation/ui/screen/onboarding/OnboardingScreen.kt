package com.suslanium.filmus.presentation.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.SecondaryButton
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.ButtonVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.OnboardingVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.SubTitle
import com.suslanium.filmus.presentation.ui.theme.Title
import com.suslanium.filmus.presentation.ui.theme.White

@Composable
fun OnboardingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.spacedBy(OnboardingVerticalSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                imageVector = ImageVector.vectorResource(id = R.drawable.amico),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(PaddingSmall, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.onboarding_title),
                    textAlign = TextAlign.Center,
                    style = Title,
                    color = White,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(id = R.string.onboarding_message),
                    textAlign = TextAlign.Center,
                    style = SubTitle,
                    color = White,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(ButtonVerticalSpacing, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AccentButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.registration)
                )
                SecondaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.sign_in)
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    OnboardingScreen()
}