package com.suslanium.filmus.data.remote.api

import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.data.remote.model.MoviesListModel
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteMoviesApiService {

    @GET(Constants.FAVORITES_LIST_URL)
    suspend fun getFavoriteMovies(): MoviesListModel

    @POST(Constants.ADD_FAVORITES_URL)
    suspend fun addFavoriteMovie(@Path("id") movieId: String)

    @DELETE(Constants.DELETE_FAVORITES_URL)
    suspend fun deleteFavoriteMovie(@Path("id") movieId: String)

}