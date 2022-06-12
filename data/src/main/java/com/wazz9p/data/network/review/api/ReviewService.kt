package com.wazz9p.data.network.review.api

import com.wazz9p.data.network.review.response.ReviewResponse
import retrofit2.http.GET

interface ReviewService {
    @GET("/reviews")
    suspend fun getReviews(): List<ReviewResponse>
}