package com.suslanium.filmus.presentation.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates

@ExperimentalMaterial3Api
val availableBirthDates = object : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis <= System.currentTimeMillis() && utcTimeMillis >= -2177452800000
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year > 1900
    }
}