package com.wazz9p.domain.repository.review

import com.wazz9p.domain.model.review.Review

interface ReviewRepository {

    suspend fun getReviewList(): List<Review>
    suspend fun sendReview(review: Review)
}