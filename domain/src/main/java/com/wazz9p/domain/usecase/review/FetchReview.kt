package com.wazz9p.domain.usecase.review

import com.wazz9p.domain.model.review.Review
import com.wazz9p.core.base.Result
import com.wazz9p.domain.repository.review.ReviewRepository
import java.io.IOException
import javax.inject.Inject

class FetchReview @Inject constructor(private val reviewRepository: ReviewRepository) {
    suspend fun execute(): Result<List<Review>> = try {
        Result.Success(data = reviewRepository.getReviewList())
    } catch (e: IOException) {
        Result.Error(e)
    }
}