package com.wazz9p.data.database.menu.entities

import androidx.room.Entity

@Entity
data class NewsEntity(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val text: String,
    val imageUrl: String?
)