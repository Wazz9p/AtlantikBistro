package com.wazz9p.data.database.menu

import com.wazz9p.data.database.menu.dao.CategoryDao
import com.wazz9p.data.mappers.menu.CategoryEntityMapper
import com.wazz9p.domain.model.menu.Category

class CategoryRoomDataSource(
    private val categoryDao: CategoryDao,
    private val mapper: CategoryEntityMapper
) : CategoryLocalDataSource {
    override suspend fun saveCategories(data: List<Category>) {
        data.map { mapper.mapToData(data = it) }
            .let { categoryDao.saveCategories(data = it) }
    }

    override suspend fun getCategories(): List<Category> {
        val data = categoryDao.getCategories()
        return data.map { mapper.mapToDomain(data = it) }
    }
}