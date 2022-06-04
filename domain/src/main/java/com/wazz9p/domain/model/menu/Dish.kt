package com.wazz9p.domain.model.menu

data class Dish(
    val id: Int,
    val name: String,
    val image: String?,
    val price: Double,
    val weight: String?,
    val description: String?,
    val categoryId: Int
)
