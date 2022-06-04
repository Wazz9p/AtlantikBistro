package com.wazz9p.domain.repository.menu

import com.wazz9p.domain.model.menu.Dish

interface MenuRepository {

    suspend fun getMenu(categoryId: Int): List<Dish>

    suspend fun getDish(dishId: Int): Dish
}