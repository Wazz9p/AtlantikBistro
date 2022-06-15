package com.wazz9p.data.repository

import com.wazz9p.data.database.news.localDataSourceImpl.NewsRoomDataSource
import com.wazz9p.data.network.news.NewsRemoteDataSource
import com.wazz9p.domain.model.news.News
import com.wazz9p.domain.repository.news.NewsRepository
import java.net.UnknownHostException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val localDataSource: NewsRoomDataSource,
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {
    override suspend fun getNewsList(): List<News> {
        return try {
            val response = remoteDataSource.getNewsList()
            localDataSource.saveNews(response)
            response
        } catch (e: Exception) {
            localDataSource.getNewsList()
        }
    }

    override suspend fun getNewsDetail(newsId: Int): News {
        TODO("Not yet implemented")
    }

}