package com.wazz9p.data.database.news.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val subtitle: String?,
    val text: String,
    val imageUrl: String?
)