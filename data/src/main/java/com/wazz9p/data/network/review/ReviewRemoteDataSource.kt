package com.wazz9p.data.network.review

import com.wazz9p.data.dataSource.ReviewDataSource
import com.wazz9p.domain.model.review.Review

interface ReviewRemoteDataSource : ReviewDataSource {
    suspend fun sendReview(data: Review)
}