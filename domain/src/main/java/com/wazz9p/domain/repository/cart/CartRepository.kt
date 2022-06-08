package com.wazz9p.domain.repository.cart

import com.wazz9p.domain.model.menu.Dish

interface CartRepository {
    suspend fun getCartDishList(): List<Dish>
    suspend fun addDish(dish: Dish)
    suspend fun deleteDish(dish: Dish)
}