package com.wazz9p.data.repository

import com.wazz9p.data.database.review.localDataSourceImpl.ReviewRoomDataSource
import com.wazz9p.data.network.review.remoteDataSourceImpl.ReviewRetrofitDataSource
import com.wazz9p.domain.model.review.Review
import com.wazz9p.domain.repository.review.ReviewRepository
import java.net.UnknownHostException
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val localDataSource: ReviewRoomDataSource,
    private val remoteDataSource: ReviewRetrofitDataSource
) : ReviewRepository {
    override suspend fun getReviewList(): List<Review> {
        return try {
            val response = remoteDataSource.getReviews()
            localDataSource.saveReview(response)
            response
        } catch (e: UnknownHostException) {
            localDataSource.getReviews()
        }
    }

    override suspend fun sendReview(review: Review) {
        if (review.name.isNotEmpty() && review.phoneNumber?.isNotEmpty() == true) {
            remoteDataSource.sendReview(data = review)
        }
    }
}