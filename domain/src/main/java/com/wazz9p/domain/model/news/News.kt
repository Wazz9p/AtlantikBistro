package com.wazz9p.domain.model.news

data class News(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val text: String,
    val imageUrl: String?
)
