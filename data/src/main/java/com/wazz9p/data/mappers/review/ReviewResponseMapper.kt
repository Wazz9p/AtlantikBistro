package com.wazz9p.data.mappers.review

import com.wazz9p.core.Mapper
import com.wazz9p.data.network.review.response.ReviewResponse
import com.wazz9p.domain.model.review.Review

class ReviewResponseMapper : Mapper<ReviewResponse, Review> {
    override fun mapToDomain(data: ReviewResponse): Review {
        TODO("Not yet implemented")
    }

    override fun mapToData(data: Review): ReviewResponse? {
        TODO("Not yet implemented")
    }
}