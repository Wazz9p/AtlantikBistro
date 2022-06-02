package com.wazz9p.data.dataSource

import com.wazz9p.data.database.menu.CategoryLocalDataSource
import com.wazz9p.data.network.menu.api.CategoryApi
import com.wazz9p.domain.model.menu.Category

class CategoryRoomDataSource(): CategoryLocalDataSource {

    override suspend fun saveCategories(data: List<Category>) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): List<Category> {
        TODO("Not yet implemented")
    }
}