package com.wazz9p.data.mappers.menu

import com.wazz9p.core.Mapper
import com.wazz9p.data.network.menu.response.MenuResponse
import com.wazz9p.domain.model.menu.Category
import com.wazz9p.domain.model.menu.Dish

class MenuResponseMapper : Mapper<MenuResponse, Dish> {

    override fun mapToDomain(data: MenuResponse): Dish {
        return Dish(
            id = data.id,
            name = data.name,
            image = data.imageRef,
            price = data.price,
            weight = data.weight,
            description = data.description,
            categoryId = data.categoryId
        )
    }

    override fun mapToData(data: Dish): MenuResponse? {
        return null
    }
}