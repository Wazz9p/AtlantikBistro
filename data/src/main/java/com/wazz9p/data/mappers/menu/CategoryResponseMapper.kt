package com.wazz9p.data.mappers.menu

import com.wazz9p.core.Mapper
import com.wazz9p.data.network.menu.response.CategoryResponse
import com.wazz9p.domain.model.menu.Category

class CategoryResponseMapper : Mapper<CategoryResponse, Category> {

    override fun mapToDomain(data: CategoryResponse): Category {
        return Category(id = data.id, name = data.name)
    }

    override fun mapToData(data: Category): CategoryResponse? {
        return null
    }
}