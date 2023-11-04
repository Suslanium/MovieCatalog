package com.suslanium.filmus.data.remote.api

import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.data.remote.model.MoviesListModel
import retrofit2.http.GET

interface FavoriteMoviesApiService {

    @GET(Constants.FAVORITES_LIST_URL)
    suspend fun getFavoriteMovies(): MoviesListModel

}