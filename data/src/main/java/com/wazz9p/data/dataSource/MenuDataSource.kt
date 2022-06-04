package com.wazz9p.data.dataSource

import com.wazz9p.domain.model.menu.Dish

interface MenuDataSource {

    suspend fun getMenu(categoryId: Int): List<Dish>

    suspend fun getDish(dishId: Int): Dish?
}