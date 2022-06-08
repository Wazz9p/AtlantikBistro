package com.wazz9p.domain.usecase.news

import com.wazz9p.domain.model.news.News
import com.wazz9p.core.base.Result
import com.wazz9p.domain.repository.news.NewsRepositroy
import java.io.IOException
import javax.inject.Inject

class FetchNewsList @Inject constructor(private val newsRepository: NewsRepositroy) {
    suspend fun execute(): Result<List<News>> = try {
        Result.Success(data = newsRepository.getNewsList())
    } catch (e: IOException) {
        Result.Error(e)
    }
}