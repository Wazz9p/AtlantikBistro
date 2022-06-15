package com.wazz9p.data.database.review

import com.wazz9p.data.dataSource.ReviewDataSource
import com.wazz9p.domain.model.review.Review

interface ReviewLocalDataSource : ReviewDataSource {
    suspend fun saveReview(data: List<Review>)
}