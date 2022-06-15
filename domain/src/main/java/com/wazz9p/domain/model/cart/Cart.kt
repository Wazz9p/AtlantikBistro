package com.wazz9p.domain.model.cart

data class Cart(
    val userName: String,
    val phone: String,
    val menu: List<CartDish>
)

data class CartDish(
    val id: Int,
    val name: String,
    val price: String
)