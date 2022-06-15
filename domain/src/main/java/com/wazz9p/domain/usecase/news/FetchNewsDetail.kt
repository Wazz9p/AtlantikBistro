package com.wazz9p.domain.usecase.news

import com.wazz9p.core.base.Result
import com.wazz9p.domain.model.news.News
import com.wazz9p.domain.repository.news.NewsRepository
import java.io.IOException
import javax.inject.Inject

class FetchNewsDetail @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(newsId: Int): Result<News> = try {
        Result.Success(data = newsRepository.getNewsDetail(newsId))
    } catch (e: IOException) {
        Result.Error(e)
    }
}