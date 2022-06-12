package com.wazz9p.data.network.news.api

import com.wazz9p.data.network.news.response.NewsResponse
import retrofit2.http.GET

interface NewsService {
    @GET("/news")
    suspend fun getNews(): List<NewsResponse>
}