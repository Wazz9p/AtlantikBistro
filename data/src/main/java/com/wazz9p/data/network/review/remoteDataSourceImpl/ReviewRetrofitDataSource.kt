package com.wazz9p.data.network.review.remoteDataSourceImpl

import com.wazz9p.data.mappers.review.ReviewResponseMapper
import com.wazz9p.data.mappers.review.SendReviewMapper
import com.wazz9p.data.network.review.ReviewRemoteDataSource
import com.wazz9p.data.network.review.api.ReviewService
import com.wazz9p.domain.model.review.Review
import java.net.UnknownHostException
import javax.inject.Inject

class ReviewRetrofitDataSource @Inject constructor(
    private val reviewApi: ReviewService,
    private val mapper: ReviewResponseMapper,
    private val sendMapper: SendReviewMapper
) : ReviewRemoteDataSource {

    override suspend fun sendReview(data: Review) {
        reviewApi.sendReviews(sendMapper.mapToData(data))
    }

    override suspend fun getReviews(): List<Review> {
        try {
            val data = reviewApi.getReviews()
            return data.reversed().map { mapper.mapToDomain(data = it) }
        } catch (e: UnknownHostException) {
            throw e
        }
    }
}