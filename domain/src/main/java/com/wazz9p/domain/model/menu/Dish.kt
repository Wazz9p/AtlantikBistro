package com.wazz9p.domain.model.menu

import android.os.Parcelable
import com.wazz9p.domain.model.cart.CartDish
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dish(
    val id: Int,
    val name: String,
    val image: String?,
    val price: String?,
    val weight: String?,
    val description: String?,
    val categoryId: Int
) : Parcelable {
    val priceTag: String
        get() = "$price р"

    val detailPriceTag: String
        get() = "В корзину за $priceTag"
}


fun Dish.toCartDish(): CartDish = CartDish(
    id = this.id,
    name = this.name,
    price = this.price.toString()
)