package com.wazz9p.data.mappers.cart

import com.wazz9p.core.Mapper
import com.wazz9p.data.database.cart.entity.CartEntity
import com.wazz9p.domain.model.menu.Dish

class CartEntityMapper : Mapper<CartEntity, Dish> {
    override fun mapToDomain(data: CartEntity): Dish = Dish(
        id = data.id,
        name = data.name,
        image = data.imageRef,
        price = data.price,
        weight = data.weight,
        description = data.description,
        categoryId = data.categoryId
    )

    override fun mapToData(data: Dish): CartEntity = CartEntity(
        id = data.id,
        name = data.name,
        imageRef = data.image,
        price = data.price,
        weight = data.weight,
        description = data.description,
        categoryId = data.categoryId
    )
}