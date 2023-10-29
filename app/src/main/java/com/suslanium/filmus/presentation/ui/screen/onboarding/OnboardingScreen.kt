package com.suslanium.filmus.presentation.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.SecondaryButton
import com.suslanium.filmus.presentation.ui.navigation.FilmusDestinations
import com.suslanium.filmus.presentation.ui.theme.Background
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.OnboardingVerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.SubTitle
import com.suslanium.filmus.presentation.ui.theme.Title
import com.suslanium.filmus.presentation.ui.theme.White
import com.suslanium.filmus.presentation.ui.theme.WidthFraction

@Composable
fun OnboardingScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(WidthFraction),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().height(338.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.amico),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(OnboardingVerticalSpacing))
            Text(
                text = stringResource(id = R.string.onboarding_title),
                textAlign = TextAlign.Center,
                style = Title,
                color = White,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(PaddingSmall))
            Text(
                text = stringResource(id = R.string.onboarding_message),
                textAlign = TextAlign.Center,
                style = SubTitle,
                color = White,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(OnboardingVerticalSpacing))
            AccentButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate(FilmusDestinations.REGISTRATION) },
                text = stringResource(id = R.string.registration)
            )
            Spacer(modifier = Modifier.height(VerticalSpacing))
            SecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate(FilmusDestinations.LOGIN) },
                text = stringResource(id = R.string.sign_in)
            )
        }
    }
}