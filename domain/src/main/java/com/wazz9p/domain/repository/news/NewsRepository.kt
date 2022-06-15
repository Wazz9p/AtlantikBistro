package com.wazz9p.domain.repository.news

import com.wazz9p.domain.model.news.News

interface NewsRepository {
    suspend fun getNewsList(): List<News>
    suspend fun getNewsDetail(newsId: Int): News
}