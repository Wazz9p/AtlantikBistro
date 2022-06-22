package com.wazz9p.data.mappers.cart

import com.wazz9p.core.Mapper
import com.wazz9p.data.network.cart.response.CartDish
import com.wazz9p.data.network.cart.response.CartResponse
import com.wazz9p.domain.model.cart.Cart


class CartResponseMapper : Mapper<CartResponse, Cart> {

    override fun mapToDomain(data: CartResponse): Cart {
        TODO("Not yet implemented")
    }

    override fun mapToData(data: Cart): CartResponse {
        val menu: List<CartDish> = data.menu.map { mapCart(it) }
        return CartResponse(
            userName = data.userName,
            phone = data.phone,
            menu = menu
        )
    }

    private fun mapCart(data: com.wazz9p.domain.model.cart.CartDish): CartDish = CartDish(
        id = data.id,
        name = data.name,
        price = data.price
    )
}