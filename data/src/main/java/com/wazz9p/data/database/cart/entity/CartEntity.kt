package com.wazz9p.data.database.cart.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageRef: String?,
    val weight: String?,
    val price: String?,
    val description: String?,
    val categoryId: Int
)
