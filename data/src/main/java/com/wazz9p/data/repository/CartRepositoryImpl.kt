package com.wazz9p.data.repository

import com.wazz9p.data.database.cart.CartLocalDataSource
import com.wazz9p.data.network.cart.CartRemoteDataSource
import com.wazz9p.domain.model.cart.Cart
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.repository.cart.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val localDataSource: CartLocalDataSource,
    private val remoteDataSource: CartRemoteDataSource
) : CartRepository {
    override suspend fun getCartDishList(): List<Dish> = localDataSource.getCart()

    override suspend fun addDish(dish: Dish) {
        localDataSource.saveCart(dish)
    }

    override suspend fun clearCart() {
        localDataSource.clearCart()
    }

    override suspend fun sendCart(cart: Cart) {
        if (cart.userName.isNotEmpty() && cart.phone.isNotEmpty()) {
            remoteDataSource.sendCart(cart)
        }
    }
}