package com.wazz9p.data.dataSource

import com.wazz9p.domain.model.menu.Category

interface CategoryDataSource {
    suspend fun getCategories(): List<Category>
}