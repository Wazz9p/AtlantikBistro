package com.wazz9p.data.database.menu

import com.wazz9p.data.dataSource.CategoryDataSource
import com.wazz9p.domain.model.menu.Category

interface CategoryLocalDataSource: CategoryDataSource {

    suspend fun saveCategories(data: List<Category>)
}