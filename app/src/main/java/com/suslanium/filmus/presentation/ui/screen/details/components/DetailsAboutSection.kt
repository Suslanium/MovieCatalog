package com.suslanium.filmus.presentation.ui.screen.details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.S16_W700
import com.suslanium.filmus.presentation.ui.theme.White
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

val decimalFormat = DecimalFormat("###,###,###,###", DecimalFormatSymbols(Locale.FRENCH))

@Composable
fun DetailsAboutSection(
    year: Int,
    country: String?,
    tagLine: String?,
    director: String?,
    budget: Int?,
    fees: Int?,
    minimalAge: Int,
    lengthMinutes: Int
) {
    Spacer(modifier = Modifier.height(PaddingLarge))
    Text(
        modifier = Modifier.padding(horizontal = PaddingMedium), text = stringResource(
            id = R.string.about_film
        ), style = S16_W700, color = White
    )
    Spacer(modifier = Modifier.height(PaddingLarge / 2))
    AboutSectionElement(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        name = stringResource(id = R.string.year),
        value = year.toString()
    )
    if (country != null) {
        Spacer(modifier = Modifier.height(PaddingLarge / 2))
        AboutSectionElement(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium),
            name = stringResource(id = R.string.country),
            value = country
        )
    }
    if (tagLine != null) {
        Spacer(modifier = Modifier.height(PaddingLarge / 2))
        AboutSectionElement(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium),
            name = stringResource(id = R.string.tagline),
            value = stringResource(id = R.string.tagline_format, tagLine)
        )
    }
    if (director != null) {
        Spacer(modifier = Modifier.height(PaddingLarge / 2))
        AboutSectionElement(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium),
            name = stringResource(id = R.string.director),
            value = director
        )
    }
    if (budget != null) {
        Spacer(modifier = Modifier.height(PaddingLarge / 2))
        AboutSectionElement(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium),
            name = stringResource(id = R.string.budget),
            value = stringResource(id = R.string.money_format, decimalFormat.format(budget))
        )
    }
    if (fees != null) {
        Spacer(modifier = Modifier.height(PaddingLarge / 2))
        AboutSectionElement(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium),
            name = stringResource(id = R.string.fees),
            value = stringResource(id = R.string.money_format, decimalFormat.format(fees))
        )
    }
    Spacer(modifier = Modifier.height(PaddingLarge / 2))
    AboutSectionElement(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        name = stringResource(id = R.string.age),
        value = stringResource(id = R.string.age_format, minimalAge)
    )
    Spacer(modifier = Modifier.height(PaddingLarge / 2))
    AboutSectionElement(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        name = stringResource(id = R.string.length),
        value = stringResource(
            id = R.string.time_format, lengthMinutes
        )
    )
}