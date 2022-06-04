package com.wazz9p.domain.repository.menu

import com.wazz9p.domain.model.menu.Category

interface CategoryRepository {

    suspend fun getCategories(): List<Category>
}