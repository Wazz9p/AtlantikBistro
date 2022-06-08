package com.wazz9p.data.database.news.entity

import androidx.room.Entity

@Entity(tableName = "news")
data class NewsEntity(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val text: String,
    val imageUrl: String?
)