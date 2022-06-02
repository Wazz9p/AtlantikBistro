package com.wazz9p.data.network.menu

import com.wazz9p.data.dataSource.CategoryDataSource
import com.wazz9p.data.mappers.menu.CategoryResponseMapper
import com.wazz9p.data.network.menu.api.CategoryService
import com.wazz9p.domain.model.menu.Category
import java.net.UnknownHostException
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(
    private val categoryApi: CategoryService,
    private val categoryResponseMapper: CategoryResponseMapper
) : CategoryDataSource {

    override suspend fun getCategories(): List<Category> {
        try {
            val data = categoryApi.getCategories()
            return data.map { categoryResponseMapper.mapToDomain(data = it) }
        } catch (e: UnknownHostException) {
            throw e
        }
    }
}