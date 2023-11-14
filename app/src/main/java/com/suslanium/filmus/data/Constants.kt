package com.suslanium.filmus.data

object Constants {
    const val TOKEN_PREFERENCES = "token_preferences"
    const val USER_PREFERENCES = "user_preferences"
    const val USER_ID_KEY = "user_id"
    const val ACCESS_TOKEN = "access_token"
    const val BASE_URL = "https://react-midterm.kreosoft.space/api/"
    const val REGISTER_URL = "account/register"
    const val LOGIN_URL = "account/login"
    const val LOGOUT_URL = "account/logout"
    const val MOVIE_LIST_URL = "movies/{page}"
    const val MOVIE_DETAILS_URL = "movies/details/{id}"
    const val USER_PROFILE_URL = "account/profile"
    const val FAVORITES_LIST_URL = "favorites"
    const val ADD_FAVORITES_URL = "favorites/{id}/add"
    const val DELETE_FAVORITES_URL = "favorites/{id}/delete"
    const val ADD_REVIEW_URL = "movie/{movieId}/review/add"
    const val EDIT_REVIEW_URL = "movie/{movieId}/review/{id}/edit"
    const val DELETE_REVIEW_URL = "movie/{movieId}/review/{id}/delete"
    const val DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val AUTH_HEADER = "Authorization"
    const val BEARER = "Bearer"
}