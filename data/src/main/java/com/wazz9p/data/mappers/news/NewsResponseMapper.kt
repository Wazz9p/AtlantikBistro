package com.wazz9p.data.mappers.news

import com.wazz9p.core.Mapper
import com.wazz9p.data.network.news.response.NewsResponse
import com.wazz9p.domain.model.news.News

class NewsResponseMapper : Mapper<NewsResponse, News> {
    override fun mapToDomain(data: NewsResponse): News = News(
        id = data.id,
        title = data.title,
        subtitle = data.subTitle,
        text = data.description,
        imageUrl = data.imageRef
    )

    override fun mapToData(data: News): NewsResponse? = null
}