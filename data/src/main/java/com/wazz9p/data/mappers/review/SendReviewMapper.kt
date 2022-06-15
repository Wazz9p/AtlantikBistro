package com.wazz9p.data.mappers.review

import com.wazz9p.core.Mapper
import com.wazz9p.data.network.review.response.SendReview
import com.wazz9p.domain.model.review.Review

class SendReviewMapper : Mapper<SendReview, Review> {
    override fun mapToDomain(data: SendReview): Review {
        TODO("Not yet implemented")
    }

    override fun mapToData(data: Review): SendReview = SendReview(
        phone = data.phoneNumber.toString(),
        message = data.message,
        name = data.name
    )
}