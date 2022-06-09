package com.wazz9p.data.mappers.news

import com.wazz9p.core.Mapper
import com.wazz9p.data.database.news.entity.NewsEntity
import com.wazz9p.domain.model.news.News

class NewsEntityMapper : Mapper<NewsEntity, News> {
    override fun mapToDomain(data: NewsEntity): News = News(
        id = data.id,
        title = data.title,
        subtitle = data.title,
        text = data.text,
        imageUrl = data.imageUrl
    )

    override fun mapToData(data: News): NewsEntity = NewsEntity(
        id = data.id,
        title = data.title,
        subtitle = data.title,
        text = data.text,
        imageUrl = data.imageUrl
    )
}