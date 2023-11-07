package com.suslanium.filmus.presentation.ui.screen.details.components.reviewdialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.suslanium.filmus.R
import com.suslanium.filmus.presentation.state.ReviewState
import com.suslanium.filmus.presentation.ui.common.AccentButton
import com.suslanium.filmus.presentation.ui.common.SecondaryButton
import com.suslanium.filmus.presentation.ui.theme.MovieCardCornerRadiusMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingLarge
import com.suslanium.filmus.presentation.ui.theme.PaddingMedium
import com.suslanium.filmus.presentation.ui.theme.PaddingSmall
import com.suslanium.filmus.presentation.ui.theme.S20_W700
import com.suslanium.filmus.presentation.ui.theme.VerticalSpacing
import com.suslanium.filmus.presentation.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewDialog(
    dismissDialog: () -> Unit,
    saveReview: () -> Unit,
    reviewTextProvider: () -> String,
    setReviewText: (String) -> Unit,
    ratingProvider: () -> Int,
    setRating: (Int) -> Unit,
    isAnonymousProvider: () -> Boolean,
    setAnonymous: (Boolean) -> Unit,
    anonymousCheckboxEnabled: Boolean,
    saveButtonEnabled: () -> Boolean,
    dialogState: () -> ReviewState
) {
    AlertDialog(
        onDismissRequest = dismissDialog, properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        ReviewDialogContent(
            dismissDialog,
            saveReview,
            reviewTextProvider,
            setReviewText,
            ratingProvider,
            setRating,
            isAnonymousProvider,
            setAnonymous,
            anonymousCheckboxEnabled,
            saveButtonEnabled,
            dialogState
        )
    }
}

@Composable
fun ReviewDialogContent(
    dismissDialog: () -> Unit,
    saveReview: () -> Unit,
    reviewTextProvider: () -> String,
    setReviewText: (String) -> Unit,
    ratingProvider: () -> Int,
    setRating: (Int) -> Unit,
    isAnonymousProvider: () -> Boolean,
    setAnonymous: (Boolean) -> Unit,
    anonymousCheckboxEnabled: Boolean,
    saveButtonEnabled: () -> Boolean,
    dialogState: () -> ReviewState
) {
    Surface(
        modifier = Modifier
            .width(328.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(MovieCardCornerRadiusMedium),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(PaddingLarge / 2)) {
            Text(text = stringResource(id = R.string.post_review), style = S20_W700, color = White)
            Spacer(modifier = Modifier.height(VerticalSpacing))
            ReviewRateBar(
                { if (dialogState() != ReviewState.Saving) setRating(it) },
                ratingProvider
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            ReviewTextField(
                reviewTextProvider,
                setReviewText,
                dialogState() == ReviewState.Error,
                if (dialogState() == ReviewState.Error) stringResource(
                    id = R.string.unknown_error
                ) else null,
                dialogState() != ReviewState.Saving
            )
            Spacer(modifier = Modifier.height(14.dp))
            ReviewCheckBox(isAnonymousProvider, { if (dialogState() != ReviewState.Saving) setAnonymous(it) }, anonymousCheckboxEnabled)
            Spacer(modifier = Modifier.height(25.dp))
            AccentButton(
                onClick = saveReview,
                text = stringResource(id = R.string.save),
                modifier = Modifier.fillMaxWidth(),
                enabled = saveButtonEnabled() && dialogState() != ReviewState.Saving,
                hasProgressIndicator = dialogState() == ReviewState.Saving
            )
            Spacer(modifier = Modifier.height(PaddingSmall))
            SecondaryButton(
                onClick = dismissDialog,
                text = stringResource(id = R.string.cancel),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}