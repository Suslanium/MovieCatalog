package com.suslanium.filmus.data.remote.api

import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.domain.entity.review.ReviewRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewApiService {

    @POST(Constants.ADD_REVIEW_URL)
    suspend fun addReview(@Path("movieId") movieId: String, @Body review: ReviewRequest)

    @PUT(Constants.EDIT_REVIEW_URL)
    suspend fun editReview(@Path("movieId") movieId: String, @Path("id") reviewId: String, @Body review: ReviewRequest)

    @DELETE(Constants.DELETE_REVIEW_URL)
    suspend fun deleteReview(@Path("movieId") movieId: String, @Path("id") reviewId: String)

}