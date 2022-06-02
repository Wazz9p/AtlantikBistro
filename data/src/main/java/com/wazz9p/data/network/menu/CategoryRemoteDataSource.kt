package com.wazz9p.data.network.menu

import com.wazz9p.data.dataSource.CategoryDataSource
import com.wazz9p.data.mappers.menu.CategoryResponseMapper
import com.wazz9p.data.network.menu.api.CategoryApi
import com.wazz9p.domain.model.menu.Category

class CategoryRemoteDataSource(
    private val categoryApi: CategoryApi,
    private val categoryResponseMapper: CategoryResponseMapper
) : CategoryDataSource {

    override suspend fun getCategories(): List<Category> {
        val data = categoryApi.getCategories()
        return data.map { categoryResponseMapper.mapToDomain(data = it) }
    }
}