package com.wazz9p.data.network.review.api

import com.wazz9p.data.network.review.response.ReviewResponse
import com.wazz9p.data.network.review.response.SendReview
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReviewService {
    @GET("/reviews")
    suspend fun getReviews(): List<ReviewResponse>

    @POST("/reviews/add")
    suspend fun sendReviews(@Body data: SendReview)
}