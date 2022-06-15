package com.wazz9p.data.network.news

import com.wazz9p.data.dataSource.NewsDataSource
import com.wazz9p.data.mappers.news.NewsResponseMapper
import com.wazz9p.data.network.news.api.NewsService
import com.wazz9p.domain.model.news.News
import java.net.UnknownHostException
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsApi: NewsService,
    private val mapper: NewsResponseMapper
) : NewsDataSource {
    override suspend fun getNewsList(): List<News> {
        try {
            val response = newsApi.getNews()
            return response.reversed().map { mapper.mapToDomain(data = it) }
        } catch (e: Exception) {
            throw e
        }
    }
}