package com.wazz9p.data.database.cart

import com.wazz9p.data.dataSource.CartDataSource
import com.wazz9p.domain.model.menu.Dish

interface CartLocalDataSource : CartDataSource {
    suspend fun getCart(): List<Dish>

    suspend fun saveCart(dish: Dish)

    suspend fun deleteDish(dish: Dish)

    suspend fun clearCart()
}