package com.wazz9p.data.database.news.localDataSourceImpl

import com.wazz9p.data.database.news.NewsLocalDataSource
import com.wazz9p.data.database.news.dao.NewsDao
import com.wazz9p.data.mappers.news.NewsEntityMapper
import com.wazz9p.domain.model.news.News
import javax.inject.Inject

class NewsRoomDataSource @Inject constructor(
    private val newsDao: NewsDao,
    private val mapper: NewsEntityMapper
) : NewsLocalDataSource {

    override suspend fun saveNews(data: List<News>) {
        data.map { mapper.mapToData(data = it) }
            .let { newsDao.saveNews(data = it) }
    }

    override suspend fun getNewsList(): List<News> {
        val data = newsDao.getNews()
        return data.reversed().map { mapper.mapToDomain(data = it) }
    }
}