package com.wazz9p.data.dataSource

import com.wazz9p.domain.model.review.Review

interface ReviewDataSource {
    suspend fun getReviews(): List<Review>
}