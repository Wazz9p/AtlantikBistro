package com.wazz9p.data.mappers.menu

import com.wazz9p.core.Mapper
import com.wazz9p.data.database.menu.entities.CategoryEntity
import com.wazz9p.domain.model.menu.Category

class CategoryEntityMapper : Mapper<CategoryEntity, Category> {

    override fun mapToDomain(data: CategoryEntity): Category {
        return Category(id = data.id, name = data.name, image = data.imageRef)
    }

    override fun mapToData(data: Category): CategoryEntity {
        return CategoryEntity(id = data.id, name = data.name, imageRef = data.image)
    }
}