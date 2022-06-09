package com.wazz9p.data.database.news

import com.wazz9p.data.dataSource.NewsDataSource
import com.wazz9p.domain.model.news.News

interface NewsLocalDataSource: NewsDataSource {

    suspend fun saveNews(data: List<News>)
}