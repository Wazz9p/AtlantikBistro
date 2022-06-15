package com.wazz9p.data.network.cart

import com.wazz9p.data.dataSource.CartDataSource
import com.wazz9p.domain.model.cart.Cart

interface CartRemoteDataSource : CartDataSource {
    suspend fun sendCart(cart: Cart)
}