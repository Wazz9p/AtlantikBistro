package com.wazz9p.data.database.review.localDataSourceImpl

import com.wazz9p.data.database.review.ReviewLocalDataSource
import com.wazz9p.data.database.review.dao.ReviewDao
import com.wazz9p.data.mappers.review.ReviewEntityMapper
import com.wazz9p.domain.model.review.Review
import javax.inject.Inject

class ReviewRoomDataSource @Inject constructor(
    private val reviewDao: ReviewDao,
    private val mapper: ReviewEntityMapper
) : ReviewLocalDataSource {
    override suspend fun saveReview(data: List<Review>) {
        data.map { mapper.mapToData(data = it) }
            .let { reviewDao.saveReview(data = it) }
    }

    override suspend fun getReviews(): List<Review> {
        val data = reviewDao.getReviews()
        return data.map { mapper.mapToDomain(data = it) }
    }
}