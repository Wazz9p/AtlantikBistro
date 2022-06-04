package com.wazz9p.domain.usecase.menu

import com.wazz9p.domain.model.menu.Category
import com.wazz9p.core.base.Result
import com.wazz9p.domain.repository.menu.CategoryRepository
import java.io.IOException
import javax.inject.Inject

class FetchCategory @Inject constructor(private val categoryRepository: CategoryRepository) {

    suspend fun execute(): Result<List<Category>> = try {
        Result.Success(data = categoryRepository.getCategories())
    } catch (e: IOException) {
        Result.Error(e)
    }
}