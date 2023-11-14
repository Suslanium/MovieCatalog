package com.suslanium.filmus.presentation.common

import java.time.format.DateTimeFormatter

object Constants {
    const val AUTH_TAG = "Auth"
    const val EMPTY_STRING = ""
    const val MODIFIED_FILM_ID = "modifiedFilmId"
    const val IS_FAVORITE = "isFavorite"
    const val NEW_USER_RATING = "userRating"
    const val NEW_RATING = "rating"
    val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    const val RATING_FORMAT = "%.1f"
}