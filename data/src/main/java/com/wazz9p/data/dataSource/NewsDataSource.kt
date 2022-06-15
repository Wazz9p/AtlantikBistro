package com.wazz9p.data.dataSource

import com.wazz9p.domain.model.news.News

interface NewsDataSource {
    suspend fun getNewsList(): List<News>
}