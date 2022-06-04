package com.wazz9p.data.database.menu.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class MenuEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageRef: String?,
    val weight: String?,
    val price: String,
    val description: String?,
    val categoryId: Int
)