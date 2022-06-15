package com.wazz9p.data.network.cart

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("name") val userName: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("menu") val menu: List<CartDish>
)

data class CartDish(
    val id: Int,
    val name: String,
    val price: String
)