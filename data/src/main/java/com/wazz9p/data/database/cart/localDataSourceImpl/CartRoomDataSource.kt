package com.wazz9p.data.database.cart.localDataSourceImpl

import com.wazz9p.data.database.cart.CartLocalDataSource
import com.wazz9p.data.database.cart.dao.CartDao
import com.wazz9p.data.mappers.cart.CartEntityMapper
import com.wazz9p.domain.model.menu.Dish
import javax.inject.Inject

class CartRoomDataSource @Inject constructor(
    private val cartDao: CartDao,
    private val mapper: CartEntityMapper
) : CartLocalDataSource {

    override suspend fun getCart(): List<Dish> {
        val data = cartDao.getCart()
        return data.map { mapper.mapToDomain(it) }
    }

    override suspend fun saveCart(dish: Dish) {
        mapper.mapToData(data = dish).let {
            cartDao.insertDish(it)
        }
    }

    override suspend fun clearCart() {
        cartDao.deleteCart()
    }


}