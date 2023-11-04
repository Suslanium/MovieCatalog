package com.suslanium.filmus.data.remote.api

import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.data.remote.model.MovieDetailsModel
import com.suslanium.filmus.data.remote.model.MoviesListModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET(Constants.MOVIE_LIST_URL)
    suspend fun getMoviesList(@Path("page") pageIndex: Int): MoviesListModel

    @GET(Constants.MOVIE_DETAILS_URL)
    suspend fun getMovieDetails(@Path("id") movieUUID: String): MovieDetailsModel

}