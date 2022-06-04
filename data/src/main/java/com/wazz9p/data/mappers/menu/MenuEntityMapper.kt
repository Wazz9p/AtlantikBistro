package com.wazz9p.data.mappers.menu

import com.wazz9p.core.Mapper
import com.wazz9p.data.database.menu.entities.MenuEntity
import com.wazz9p.domain.model.menu.Dish

class MenuEntityMapper : Mapper<MenuEntity, Dish> {

    override fun mapToDomain(data: MenuEntity): Dish = Dish(
        id = data.id,
        name = data.name,
        image = data.imageRef,
        price = data.price,
        weight = data.weight,
        description = data.description,
        categoryId = data.categoryId
    )

    override fun mapToData(data: Dish): MenuEntity = MenuEntity(
        id = data.id,
        name = data.name,
        imageRef = data.image,
        price = data.price,
        weight = data.weight,
        description = data.description,
        categoryId = data.categoryId
    )
}