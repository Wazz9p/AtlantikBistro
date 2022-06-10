package com.wazz9p.data.mappers.review

import com.wazz9p.core.Mapper
import com.wazz9p.data.database.review.entity.ReviewEntity
import com.wazz9p.domain.model.review.Review

class ReviewEntityMapper : Mapper<ReviewEntity, Review> {
    override fun mapToDomain(data: ReviewEntity): Review = Review(
        name = data.userName,
        phoneNumber = data.phoneNumber,
        message = data.message,
        adminMessage = data.adminMessage
    )

    override fun mapToData(data: Review): ReviewEntity = ReviewEntity(
        userName = data.name,
        phoneNumber = data.phoneNumber,
        message = data.message,
        adminMessage = data.adminMessage
    )
}