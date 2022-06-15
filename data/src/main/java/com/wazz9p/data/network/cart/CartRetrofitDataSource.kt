package com.wazz9p.data.network.cart

import com.wazz9p.data.mappers.cart.CartResponseMapper
import com.wazz9p.data.network.cart.api.CartService
import com.wazz9p.domain.model.cart.Cart
import javax.inject.Inject

class CartRetrofitDataSource @Inject constructor(
    private val cartApi: CartService,
    private val mapper: CartResponseMapper
) : CartRemoteDataSource {
    override suspend fun sendCart(cart: Cart) {
        cartApi.sendCart(mapper.mapToData(cart))
    }
}